package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface IncomingUserModule {

    /**
     * Initializes the module, making the object ready to accept incoming users.
     */
    void init();

    /**
     * Waits for an incoming client.
     * @return a User object representing a newly connected client.
     * @throws InterruptedException if the thread was interrupted during the process (i.e.: close() was called on the user greeter)
     */
    User newUser() throws InterruptedException;

    /**
     * Closes the module, freeing all the possible resources it allocates (e.g.: a stream).
     */
    void close();

}
