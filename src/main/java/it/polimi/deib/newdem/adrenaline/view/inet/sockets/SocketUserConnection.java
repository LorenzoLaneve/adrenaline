package it.polimi.deib.newdem.adrenaline.view.inet.sockets;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.io.*;
import java.net.Socket;

/**
 * A socket user connection is a user connection that uses Java Sockets to
 * send and receive user events from the other side.
 */
public class SocketUserConnection extends UserConnectionBase {

    private InputStream input;

    private ObjectOutputStream output;

    private Thread listeningThread;

    private boolean closeRequested;


    /**
     * Initializes a new socket user connection.
     * @param observedSocket the socket that has to be read/written to for incoming/outgoing events.
     * @param user the user object representing the user that will generate/receive the events.
     * @throws IOException If a socket error occurs.
     */
    public SocketUserConnection(Socket observedSocket, User user) throws IOException {
        super(user);

        this.input = observedSocket.getInputStream();
        this.output = new ObjectOutputStream(observedSocket.getOutputStream());

        this.listeningThread = null;

        this.closeRequested = true;
    }

    public SocketUserConnection(String host, int port, User user) throws IOException {
        this(new Socket(host, port), user);
    }


    @Override
    public void start() {
        this.closeRequested = false;

        this.listeningThread = new Thread(this::listen);
        this.listeningThread.start();

        super.start();
    }

    @Override
    public void sendEvent(UserEvent event) {
        try {
            output.writeObject(event);
        } catch (Exception x) {
            close();
        }
    }

    @Override
    public void close() {
        if (closeRequested) return;

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