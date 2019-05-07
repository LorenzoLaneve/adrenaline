package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class User {

    private String name;

    private UserConnection boundConnection;

    /**
     * Initializes a new, empty user.
     */
    public User() {
        this.name = null;
        this.boundConnection = null;
    }

    /**
     * Returns the nickname given the user is logged with.
     * @apiNote This may return {@code null} if the underlying connection has not received the name yet.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the user's name to the given name. Please note that no check on the string is done by this method, nor any event will be triggered.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Binds the given connection to the user.
     * This means that further events sent and received from the connection will be associated to this user.
     */
    public void bindConnection(UserConnection connection) {
        this.boundConnection = connection;
    }

    /**
     * Returns the UserConnection object that has to be used to communicate with the user.
     * @apiNote This can also return {@code null} if the client associated with the user is not connected.
     */
    public UserConnection getBoundConnection() {
        return boundConnection;
    }

}
