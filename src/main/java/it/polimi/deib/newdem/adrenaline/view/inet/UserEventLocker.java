package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

/**
 * Utility class that provides a method that blocks the caller thread until a UserEvent
 * of type {@code T} arrives from the given connection.
 */
public class UserEventLocker<T extends UserEvent> {

    private T receivedEvent;

    /**
     * Blocks a thread until an event of type {@code T} is received by the given connection.
     * @param eventClass The class of {@code T}.
     */
    public synchronized T waitOnEvent(Class<T> eventClass, UserConnection connection) throws InterruptedException {
        UserEventSubscriber<T> subscriber = this::receiveEvent;
        this.receivedEvent = null;

        connection.subscribeEvent(eventClass, subscriber);

        try {
            while (receivedEvent == null) wait();
        } finally {
            connection.unsubscribeEvent(eventClass, subscriber);
        }

        return receivedEvent;
    }

    private synchronized void receiveEvent(UserConnection connection, T event) {
        this.receivedEvent = event;
        notifyAll();
    }

}
