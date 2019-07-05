package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User connection that uses the Java RMI technology to establish the exchange of
 * messages between the two end points (RMIEndpoint objects).
 *
 * The user connection will call the opposite connection's RMI end point to retrieve
 * new messages, eventually blocking the thread until a message is ready.
 */
public class RMIUserConnection extends UserConnectionBase {

    private RMIEndpointImpl localEndpoint;

    private RMIEndpoint remoteEndpoint;

    private Thread listeningThread;

    private boolean closeRequested;

    /**
     * Starts a new RMI user connection trying to connect to the given RMI server greeter.
     * The server greeter object is retrieved from the server's RMI registry and will create the RMI endpoint used
     * by this connection to read messages.
     * @param serverGreeter The greeter owned by the server.
     * @param localEndpoint The RMIEndpoint owned by the caller that will provide messages to the other side.
     * @param user The User object representing the user associated with this connection.
     * @throws RemoteException If an RMI-related error occurs while creating the connection.
     */
    public RMIUserConnection(RMIServerGreeter serverGreeter, RMIEndpointImpl localEndpoint, User user) throws RemoteException {
        super(user);

        this.localEndpoint = localEndpoint;
        localEndpoint.setConnection(this);

        this.remoteEndpoint = serverGreeter.connect(localEndpoint);
    }

    /**
     * Starts a new RMI user connection between the given end points.
     * @param remoteEndpoint The RMIEndpoint owned by the other side that will provide messages to the caller.
     * @param localEndpoint The RMIEndpoint owned by the caller that will provide messages to the other side.
     * @param user The User object representing the user associated with this connection.
     */
    public RMIUserConnection(RMIEndpoint remoteEndpoint, RMIEndpointImpl localEndpoint, User user) {
        super(user);

        this.localEndpoint = localEndpoint;
        localEndpoint.setConnection(this);

        this.remoteEndpoint = remoteEndpoint;
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
        localEndpoint.sendEvent(event);
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
            UnicastRemoteObject.unexportObject(localEndpoint, true);
        } catch (NoSuchObjectException e) {
            // nothing to do here.
        }

        localEndpoint.close();

        super.close();
    }

    private void listen() {
        try {

            while (!closeRequested) {
                notifyEvent(remoteEndpoint.readEvent());
            }

        } catch (Exception x) {
            Thread.currentThread().interrupt();
        } finally {
            close();
        }
    }

}
