package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.IncomingUserModule;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

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

    public synchronized void notifyNewUser(User user) {
        this.backlog.add(user);
        notifyAll();
    }

}
