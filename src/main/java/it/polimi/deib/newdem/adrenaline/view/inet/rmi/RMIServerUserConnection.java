package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RMIServerUserConnection implements UserConnection {

    private RMIServerEndpointImpl serverEndpoint;

    private List<UserConnectionReceiver> receiverList;

    private User user;

    /**
     * Initializes a new socket user connection
     * @param user the user object representing the user that will generate/receive the events.
     * @throws RemoteException If RMI throws an error during the construction of the object.
     */
    public RMIServerUserConnection(RMIServerEndpointImpl serverEndpoint, User user) throws RemoteException {
        this.receiverList = new ArrayList<>();

        this.serverEndpoint = serverEndpoint;
        this.user = user;

        user.bindConnection(this);
        serverEndpoint.setConnection(this);
    }


    @Override
    public void start() {
        // nothing to do here
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
            serverEndpoint.sendEvent(event);
        } catch (Exception x) {
            close();
            // TODO is this a good behaviour?
        }
    }

    @Override
    public void close() {


        user.bindConnection(null);
    }

    protected void notifyEvent(UserEvent event) {
        for (UserConnectionReceiver receiver : receiverList) {
            event.notifyEvent(this, receiver);
        }
    }

}
