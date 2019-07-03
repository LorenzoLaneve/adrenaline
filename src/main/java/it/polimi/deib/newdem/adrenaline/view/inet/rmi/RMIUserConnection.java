package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIUserConnection extends UserConnectionBase {

    private RMIEndpointImpl localEndpoint;

    private RMIEndpoint remoteEndpoint;

    private Thread listeningThread;

    private boolean closeRequested;

    public RMIUserConnection(RMIServerGreeter serverGreeter, RMIEndpointImpl localEndpoint, User user) throws RemoteException {
        super(user);

        this.localEndpoint = localEndpoint;
        localEndpoint.setConnection(this);

        this.remoteEndpoint = serverGreeter.connect(localEndpoint);
    }

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
