package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;

    private UserConnection boundConnection;

    private List<UserListener> listeners;

    private List<UserConnectionReceiver> receivers;

    /**
     * Initializes a new, empty user.
     */
    public User() {
        this.name = null;
        this.boundConnection = null;

        this.listeners = new ArrayList<>();
        this.receivers = new ArrayList<>();
    }

    /**
     * Adds the given object to the listeners of the current user.
     */
    public void addListener(UserListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given object from the listeners of the current user.
     * The object will no longer receive updates about the user connection.
     */
    public void removeListener(UserListener listener) {
        listeners.remove(listener);
    }

    /**
     * Returns the nickname given the user is logged with.
     * @apiNote This may return {@code null} if the underlying connection has not received the name yet.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the user's name to the given name. Please note that no check on the string is done by this method.
     */
    public void setName(String name) {
        this.name = name;

        for (UserListener listener : listeners) {
            listener.userDidChangeName(this, name);
        }
    }

    /**
     * Binds the given connection to the user.
     * This means that further events sent and received from the connection will be associated to this user.
     * @implNote All the connection receivers associated to this user will be kept and passed to the new connection,
     * while being removed from the old connection.
     */
    public void bindConnection(UserConnection connection) {
        UserConnection oldConnection = this.boundConnection;
        this.boundConnection = connection;

        if (oldConnection != null) {
            for (UserConnectionReceiver receiver : receivers) {
                oldConnection.removeReceiver(receiver);
            }
        }

        if (boundConnection != null) {
            for (UserConnectionReceiver receiver : receivers) {
                boundConnection.addReceiver(receiver);
            }
        }

        for (UserListener listener : new ArrayList<>(listeners)) {
            listener.userDidChangeConnection(this, oldConnection, boundConnection);
        }
    }

    /**
     * Returns the UserConnection object that has to be used to communicate with the user.
     * @apiNote This can also return {@code null} if the client associated with the user is not connected.
     */
    private UserConnection getBoundConnection() {
        return boundConnection;
    }

    /**
     * Adds the given UserConnectionReceiver to the objects that have to receive events from the user's connection.
     */
    public void addReceiver(UserConnectionReceiver receiver) {
        if (receiver == null)
            throw new IllegalArgumentException("The given receiver must be non-null.");

        this.receivers.add(receiver);

        if (isConnected()) {
            getBoundConnection().addReceiver(receiver);
        }
    }

    /**
     * Removes the given UserConnectionReceiver so that it will not receive further events from this user.
     */
    public void removeReceiver(UserConnectionReceiver receiver) {
        if (receiver == null)
            throw new IllegalArgumentException("The given receiver must be non-null");

        this.receivers.remove(receiver);

        if (isConnected()) {
            getBoundConnection().removeReceiver(receiver);
        }
    }

    /**
     * Sends an event to this user using their connection.
     * @apiNote The method will do nothing if the user is disconnected.
     */
    public void sendEvent(UserEvent event) {
        if (isConnected()) {
            getBoundConnection().sendEvent(event);
        }
    }

    /**
     * Returns whether the user has an active connection.
     */
    public boolean isConnected() {
        return getBoundConnection() != null;
    }

}
