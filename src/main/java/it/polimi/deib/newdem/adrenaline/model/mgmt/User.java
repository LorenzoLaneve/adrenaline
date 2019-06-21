package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventSubscriber;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

    private String name;

    private UserConnection boundConnection;

    private List<UserListener> listeners;

    private HashMap<Class<? extends UserEvent>, List<UserEventSubscriber>> subscribers;

    /**
     * Initializes a new, empty user.
     */
    public User() {
        this.name = null;
        this.boundConnection = null;

        this.listeners = new ArrayList<>();
        this.subscribers = new HashMap<>();
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
            oldConnection.clearSubscribers();
        }

        if (boundConnection != null) {
            boundConnection.copySubscribers(subscribers);
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
     * Adds the given event subscriber to the objects that have to receive events from the user's connection.
     */
    public <T extends UserEvent> void subscribeEvent(Class<T> eventClass, UserEventSubscriber<T> subscriber) {
        if (subscriber == null)
            throw new IllegalArgumentException("The given subscriber must be non-null.");

        subscribers.computeIfAbsent(eventClass, key -> new ArrayList<>()).add(subscriber);

        if (isConnected()) {
            getBoundConnection().subscribeEvent(eventClass, subscriber);
        }
    }

    /**
     * Removes the given event subscriber so that it will not receive further events from this user.
     */
    public <T extends UserEvent> void unsubscribeEvent(Class<T> eventClass, UserEventSubscriber<T> subscriber) {
        if (subscribers == null)
            throw new IllegalArgumentException("The given subscriber must be non-null");

        List<UserEventSubscriber> subList = subscribers.get(eventClass);
        if (subList != null) {
            subList.remove(subscriber);
        }

        if (isConnected()) {
            getBoundConnection().unsubscribeEvent(eventClass, subscriber);
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

    /**
     * Binds the given user's connection to this user.
     * The given user will no longer have a connection and should be freed.
     */
    public void takeOverConnection(User user) {
        bindConnection(user.getBoundConnection());
        user.bindConnection(null);
    }

}
