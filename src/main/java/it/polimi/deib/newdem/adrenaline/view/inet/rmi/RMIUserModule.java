package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.IncomingUserModule;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code IncomingUserModule} involving the response of Remote Method
 * Invocations requested by external hosts. This class involves the creation and binding of a
 * {@code RMIServerGreeter} to the server's RMI registry, that will provide connecting clients
 * {@code RMIEndpoints} that they can use to receive messages from the server.
 */
public class RMIUserModule implements IncomingUserModule {

    private RMIServerGreeter greeter;

    private List<User> backlog;

    private Registry registry;

    private String objectURI;


    public RMIUserModule(int port, String uri) throws RemoteException {
        this.backlog = new ArrayList<>();

        this.registry = LocateRegistry.createRegistry(port);

        this.greeter = new RMIServerGreeterImpl(this);

        this.objectURI = uri;
        registry.rebind(objectURI, greeter);
    }

    @Override
    public void init() {
        // nothing to init.
    }

    @Override
    public synchronized User newUser() {
        try {
            while (backlog.isEmpty()) wait();

            return backlog.remove(0);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override
    public void close() {
        try {
            registry.unbind(objectURI);
        } catch (Exception x) {
            // nothing to do here.
        }
    }

    synchronized void notifyNewUser(User user) {
        this.backlog.add(user);
        notifyAll();
    }

}
