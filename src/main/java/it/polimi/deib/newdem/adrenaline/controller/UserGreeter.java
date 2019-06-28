package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.IncomingUserModule;

import java.util.ArrayList;
import java.util.List;

/**
 * The user greeter is an object that takes various user modules that accept new users based on different criterias.
 * For example, the user greeter will listen from new users coming from different sources (i.e.: ServerSocket or RMI).
 * This class deals with sincronization, allocation and deallocation of the modules, and the wait for new users.
 */
public class UserGreeter {

    private List<IncomingUserModule> modules;

    private List<Thread> threads;

    private List<User> backlog;

    private boolean closeRequested;

    private boolean ready;

    /**
     * Create a new, empty user greeter.
     */
    public UserGreeter() {
        this.modules = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.backlog = new ArrayList<>();

        this.closeRequested = true;
        this.ready = false;
    }

    /**
     * Adds the given module to the modules that have to be used to retrieve incoming users.
     * @throws IllegalArgumentException if the given module is null.
     * @throws InvalidStateException if this method is called after the {@code init()} method.
     * This is to avoid adding new modules while the already added modules are running.
     */
    public void addUserModule(IncomingUserModule module) throws InvalidStateException {
        if (module == null) {
            throw new IllegalArgumentException("The module passed to the greeter must be non-null.");
        }

        if (ready) {
            throw new InvalidStateException("The user greeter is already initialized and cannot accept any new modules.");
        }

        modules.add(module);
    }

    /**
     * Initializes all the modules by calling IncomingUserModule.init() on all the added modules.
     * @implNote This is done on the current thread.
     * If init() is called on an already initialized user greeter, the object will not be initialized again.
     */
    public void init() {
        if (!ready) {
            this.closeRequested = false;
            for (IncomingUserModule module : modules) {
                module.init();
            }
            ready = true;
        }
    }

    /**
     * Starts all the modules. A new thread for each added module is initialized and run,
     * and it will continuously ask the associated module for new users by calling IncomingUserModule.newUser().
     * @throws InvalidStateException if init() was not called on the same instance before this method.
     * @implNote and {@code null} module returned by the module through {@code newUser()} will cause the receiver thread to immediately interrupt.
     */
    public void start() throws InvalidStateException {
        if (!ready) {
            throw new InvalidStateException("Instance not initialized. Please discharge init() on this object first.");
        }

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
     * @implNote if close() is called on an already closed user greeter, the discharge will be ignored.
     */
    public synchronized void close() {
        if (!closeRequested) {
            closeRequested = true;

            for (Thread thread : threads) {
                thread.interrupt();
            }
            threads.clear();
            backlog.clear();

            for (IncomingUserModule module : modules) {
                module.close();
            }
            ready = false;
        }
    }

    /**
     * Adds the users returned by the module in the user queue of the greeter.
     */
    private void runModule(IncomingUserModule module) {

        while (!closeRequested) {
            try {
                User newUser = module.newUser();
                if (newUser == null) {
                    Thread.currentThread().interrupt();
                }

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
