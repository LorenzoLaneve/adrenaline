package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

/**
 * An incoming user modules consists of a sort of source for users that can join the server.
 * These modules are user by the UserGreeter object: the user greeter keeps on calling
 * {@code IncomingUserModule#newUser()}, and the user modules should wait until a new user is connected.
 * Also methods to initialize and dispose the used resources are provided by this interface.
 */
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
