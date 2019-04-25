package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket.messages.SocketMessageReader;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketUserConnection implements UserConnection {

    private Socket socket;

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
        this.socket = observedSocket;

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
            event.sendEvent(new SocketUserConnectionSender(new DataOutputStream(output)));
        } catch (ConnectionException x) {
            close(); // TODO is this a good behaviour?
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
        try (DataInputStream dis = new DataInputStream(input)) {

            while (!closeRequested) {

                int messageType = dis.readInt();

                SocketMessageReader reader = SocketMessageReader.fromMessageType(messageType);

                UserEvent event = reader.make(dis);

                for (UserConnectionReceiver receiver : receiverList) {
                    event.notifyEvent(this, receiver);
                }
            }

        } catch (IOException | InvalidMessageException x) {
            for (UserConnectionReceiver receiver : receiverList) {
                receiver.connectionDidClose(this);
            }

            Thread.currentThread().interrupt();
        }
    }

}
