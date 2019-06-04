package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerEndpointImpl extends UnicastRemoteObject implements RMIServerEndpoint {

    private RMIServerUserConnection connection;

    private transient List<UserEvent> outgoingEvents;


    public RMIServerEndpointImpl() throws RemoteException {
        super();

        this.outgoingEvents = new ArrayList<>();
    }

    public void setConnection(RMIServerUserConnection connection) {
        this.connection = connection;
    }

    protected synchronized void sendEvent(UserEvent event) {
        outgoingEvents.add(event);
        notifyAll();
    }

    @Override
    public synchronized UserEvent readEvent() {

        try {
            while (outgoingEvents.isEmpty()) {
                wait();
            }

        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
        }

        return outgoingEvents.remove(0);
    }

    @Override
    public void writeEvent(UserEvent event) {
        connection.notifyEvent(event);
    }

}
