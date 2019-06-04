package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIEndpointImpl extends UnicastRemoteObject implements RMIEndpoint {

    private transient RMIUserConnection connection;

    private transient List<UserEvent> outgoingEvents;


    public RMIEndpointImpl() throws RemoteException {
        super();

        this.outgoingEvents = new ArrayList<>();
    }

    public void setConnection(RMIUserConnection connection) {
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

}
