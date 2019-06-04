package it.polimi.deib.newdem.adrenaline.view.inet.sockets;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.io.*;
import java.net.Socket;

public class SocketUserConnection extends UserConnectionBase {

    private InputStream input;

    private OutputStream output;

    private Thread listeningThread;

    private boolean closeRequested;


    /**
     * Initializes a new socket user connection
     * @param observedSocket the socket that has to be read/written to for incoming/outgoing events.
     * @param user the user object representing the user that will generate/receive the events.
     * @throws IOException If a socket error occurs.
     */
    public SocketUserConnection(Socket observedSocket, User user) throws IOException {
        super(user);

        this.input = observedSocket.getInputStream();
        this.output = observedSocket.getOutputStream();

        this.listeningThread = null;

        this.closeRequested = true;
    }


    @Override
    public void start() {
        this.closeRequested = false;

        this.listeningThread = new Thread(this::listen);
        this.listeningThread.start();
    }

    @Override
    public void sendEvent(UserEvent event) {
        try {
            ObjectOutputStream dos = new ObjectOutputStream(output);

            dos.writeObject(event);
        } catch (Exception x) {
            close();
            // TODO is this a good behaviour?
        }
    }

    @Override
    public void close() {
        synchronized (this) {
            closeRequested = true;
        }

        if (listeningThread != null) {
            listeningThread.interrupt();

            listeningThread = null;
        }

        try {
            input.close();
        } catch (IOException x) {
            // no problem
        }

        try {
            output.close();
        } catch (IOException x) {
            // no problem
        }

        super.close();
    }


    private void listen() {
        try (ObjectInputStream dis = new ObjectInputStream(input)) {

            while (!closeRequested) {
                notifyEvent((UserEvent) dis.readObject());
            }

        } catch (Exception x) {
            Thread.currentThread().interrupt();
        } finally {
            close();
        }
    }

}