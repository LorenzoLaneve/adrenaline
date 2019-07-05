package it.polimi.deib.newdem.adrenaline.view;

/**
 * A client-only view that asks the user for a username.
 */
public interface UsernameView {

    /**
     * Blocks the current thread to ask the username to the user, which then will be returned.
     */
    String prompt();

    /**
     * Shows an error to the user, notifying that the entered name could not be used.
     * @param message A user-readable string explaining the error.
     */
    void reportError(String message);

    /**
     * Notifies the user that the entered username has been accepted.
     */
    void reportSuccess();

}
