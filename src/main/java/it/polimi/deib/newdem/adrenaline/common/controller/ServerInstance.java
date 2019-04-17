package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerInstance {

    private Logger log;

    private UserGreeter greeter;

    private LobbyRegistry lobbyRegistry;

    private UserRegistry userRegistry;

    private boolean ready;

    /**
     * Creates a new server instance.
     * @param log The Logger object that will accept incoming diagnostics from the server
     * @param config The configuration object the server will use
     */
    public ServerInstance(Logger log, Config config) {
        this.log = log;
        this.greeter = new UserGreeter();

        this.ready = false;

        addUserModules(config);
    }

    private void addUserModules(Config config) {
        try {
            if (config.isSocketActive()) {
                try {
                    greeter.addUserModule(new SocketUserModule(config.getSocketPort()));

                    getLogger().log(Level.INFO, String.format("Socket module successfully added. Listening to TCP port %d", config.getSocketPort()));
                } catch (IOException e) {
                    getLogger().log(Level.SEVERE, "Could not create Socket Module: " + e.getMessage());
                }

            }

            if (config.isRMIActive()) {
                greeter.addUserModule(new RMIUserModule());
            }
        } catch (InvalidStateException x) {
            getLogger().log(Level.SEVERE, "Could not add modules to the user greeter because it was already initialized.");
        }
    }

    /**
     * Returns the Logger object for the log this server should report diagnostics to.
     */
    public Logger getLogger() {
        return log;
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
                getLogger().log(Level.INFO, String.format("User %s joined the server.", incomingUser.getName()));
            } catch (Exception e) {
                getLogger().log(Level.SEVERE, "Server encountered error: "+ e.getMessage());

                stopServer = true;
            }
        }

        greeter.close();

    }

}
