package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

import java.util.ArrayList;
import java.util.List;

public class UserGreeter {

    private List<IncomingUserModule> modules;

    private List<Thread> threads;

    private List<User> backlog;

    private boolean closeRequested;

    /**
     * Create a new, empty user greeter.
     */
    public UserGreeter() {
        this.modules = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.backlog = new ArrayList<>();

        this.closeRequested = false;
    }

    /**
     * Adds the given module to the modules that have to be used to retrieve incoming users.
     */
    public void addUserModule(IncomingUserModule module) {
        modules.add(module);
    }

    /**
     * Initializes all the modules by calling the method IncomingUserModule.init() on the current thread.
     */
    public void init() {
        for (IncomingUserModule module : modules) {
            module.init();
        }
    }

    /**
     * Starts all the modules, by calling UserGreeter.runModule() on a separate thread for each module.
     */
    public void start() {
        for (IncomingUserModule module : modules) {
            Thread moduleThread = new Thread(() -> runModule(module));
            threads.add(moduleThread);

            moduleThread.start();
        }
    }

    /**
     * Waits for a client, listening for a user that may connect through any of the added modules.
     * The method will return when a user has successfully established the connection according to the criteria defined by the corresponding module.
     * @return A User object representing the newly connected client.
     * @throws InterruptedException if the current thread receives an interrupt while waiting.
     */
    public synchronized User accept() throws InterruptedException {

        while (backlog.isEmpty()) wait();

        return backlog.remove(0);
    }

    /**
     * Closes all the associated modules by killing the threads and then calls, for each module, IncomingUserModule.close() on the current thread.
     */
    public void close() {
        closeRequested = true;

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (IncomingUserModule module : modules) {
            module.close();
        }
    }

    /**
     * Adds the users returned by the module in the user queue of the greeter.
     */
    private void runModule(IncomingUserModule module) {

        while (!closeRequested) {
            try {
                User newUser = module.newUser();

                synchronized (this) {
                    backlog.add(newUser);
                    notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
