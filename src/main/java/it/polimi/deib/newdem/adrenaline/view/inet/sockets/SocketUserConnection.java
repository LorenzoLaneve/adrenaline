package it.polimi.deib.newdem.adrenaline.view.inet.sockets;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketUserConnection implements UserConnection {

    private InputStream input;

    private OutputStream output;

    private List<UserConnectionReceiver> receiverList;

    private Thread listeningThread;

    private User user;

    private boolean closeRequested;


    /**
     * Initializes a new socket user connection
     * @param observedSocket the socket that has to be read/written to for incoming/outgoing events.
     * @param user the user object representing the user that will generate/receive the events.
     * @throws IOException If a socket error occurs.
     */
    public SocketUserConnection(Socket observedSocket, User user) throws IOException {
        this.input = observedSocket.getInputStream();
        this.output = observedSocket.getOutputStream();

        this.receiverList = new ArrayList<>();
        this.listeningThread = null;

        this.closeRequested = true;

        this.user = user;
        user.bindConnection(this);
    }


    @Override
    public void start() {
        this.closeRequested = false;

        this.listeningThread = new Thread(this::listen);
        this.listeningThread.start();
    }

    @Override
    public User getUser() {
        return user;
    }


    @Override
    public void addReceiver(UserConnectionReceiver receiver) {
        receiverList.add(receiver);
    }

    @Override
    public void removeReceiver(UserConnectionReceiver receiver) {
        receiverList.remove(receiver);
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

        user.bindConnection(null);
    }


    private void listen() {
        try (ObjectInputStream dis = new ObjectInputStream(input)) {

            while (!closeRequested) {

                UserEvent event = (UserEvent) dis.readObject();

                for (UserConnectionReceiver receiver : receiverList) {
                    event.notifyEvent(this, receiver);
                }

            }

        } catch (Exception x) {
            Thread.currentThread().interrupt();
        } finally {
            for (UserConnectionReceiver receiver : receiverList) {
                receiver.connectionDidClose(this);
            }
        }
    }

}