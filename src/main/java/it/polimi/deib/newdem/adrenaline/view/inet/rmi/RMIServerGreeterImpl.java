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
    public RMIServerEndpoint connect() throws RemoteException {
        User user = new User();

        RMIServerEndpointImpl serverEndpoint = new RMIServerEndpointImpl();

        RMIServerUserConnection connection = new RMIServerUserConnection(serverEndpoint, user);
        connection.start();

        userModule.notifyNewUser(user);

        return serverEndpoint;
    }

}
