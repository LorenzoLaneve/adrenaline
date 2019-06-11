package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIUserModule;
import it.polimi.deib.newdem.adrenaline.view.inet.sockets.SocketUserModule;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerInstance {

    private Logger log;

    private UserGreeter greeter;

    private LobbyRegistry lobbyRegistry;

    private UserRegistry userRegistry;

    private boolean ready;

    private Config currentConfig;

    /**
     * Creates a new server instance.
     * @param log The Logger object that will accept incoming diagnostics from the server
     * @param config The configuration object the server will use
     */
    public ServerInstance(Logger log, Config config) {
        this.log = log;
        this.greeter = new UserGreeter();
        this.currentConfig = config;

        this.ready = false;

        addUserModules(config);
    }

    private void addUserModules(Config config) {
        try {
            if (config.isSocketActive()) {
                try {
                    greeter.addUserModule(new SocketUserModule(config.getSocketPort()));

                    getLogger().info(String.format("Socket module successfully added. Listening to TCP port %d", config.getSocketPort()));
                } catch (IOException e) {
                    getLogger().severe("Could not create Socket Module: " + e.getMessage());
                }

            }

            if (config.isRMIActive()) {
                try {
                    greeter.addUserModule(new RMIUserModule(config.getRMIPort(), config.getRMIIdentifier()));

                    getLogger().info(String.format("RMI module successfully added. Listening to TCP port %d with URI /%s", config.getRMIPort(), config.getRMIIdentifier()));
                } catch (RemoteException e) {
                    getLogger().severe("Could not create RMI Module: " + e.getMessage());
                }
            }
        } catch (InvalidStateException x) {
            getLogger().log(Level.SEVERE, "Could not add modules to the user greeter because it was already initialized.");
        }
    }

    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    public LobbyRegistry getLobbyRegistry() {
        return lobbyRegistry;
    }


    /**
     * Returns the Logger object for the log this server should report diagnostics to.
     */
    public Logger getLogger() {
        return log;
    }

    /**
     * Returns the configuration object used by this server instance.
     */
    public Config getCurrentConfig() {
        return currentConfig;
    }

    /**
     * Initializes the server instance.
     */
    public void init() {
        greeter.init();

        lobbyRegistry = new LobbyRegistry(this);
        userRegistry = new UserRegistry(this);

        ready = true;
    }

    /**
     * Starts the server instance in the current thread.
     * @throws InvalidStateException if the server instance was not initialized before starting.
     * @implNote To initialize the server instance, please call ServerInstance.init()
     */
    public void start() throws InvalidStateException {

        if (!ready || userRegistry == null || lobbyRegistry == null) {
            throw new InvalidStateException("This server instance was not initialized. Please call init() before starting.");
        }

        greeter.start();

        boolean stopServer = false;
        while (!stopServer) {
            try {
                User incomingUser = greeter.accept();

                userRegistry.registerUser(incomingUser);
            } catch (Exception e) {
                getLogger().log(Level.SEVERE, "Server encountered error: "+ e.getMessage());

                stopServer = true;
            }
        }

        greeter.close();

    }

}
