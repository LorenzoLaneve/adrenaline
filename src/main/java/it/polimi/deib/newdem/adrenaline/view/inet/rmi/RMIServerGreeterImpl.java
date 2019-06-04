package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerGreeterImpl extends UnicastRemoteObject implements RMIServerGreeter {

    private transient RMIUserModule userModule;

    private RMIServerGreeterImpl() throws RemoteException {
        super();
    }

    public RMIServerGreeterImpl(RMIUserModule userModule) throws RemoteException {
        super();

        this.userModule = userModule;
    }

    @Override
    public RMIEndpoint connect(RMIEndpoint clientEndpoint) throws RemoteException {
        if (clientEndpoint == null) {
            throw new IllegalArgumentException("An end point has to be provided in order to successfully connect.");
        }

        User user = new User();

        RMIEndpointImpl serverEndpoint = new RMIEndpointImpl();

        RMIUserConnection connection = new RMIUserConnection(clientEndpoint, serverEndpoint, user);
        connection.start();

        userModule.notifyNewUser(user);

        return serverEndpoint;
    }

}
