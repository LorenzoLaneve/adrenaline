package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

public class UserEventLocker<T extends UserEvent> {

    private UserEventSubscriber<T> subscriber;

    private Class<T> eventClass;

    private T receivedEvent;

    public synchronized T waitOnEvent(Class<T> eventClass, UserConnection connection) throws InterruptedException {
        this.subscriber = this::receiveEvent;
        this.eventClass = eventClass;
        this.receivedEvent = null;

        connection.subscribeEvent(eventClass, subscriber);
        while (receivedEvent == null) wait();

        return receivedEvent;
    }

    private synchronized void receiveEvent(UserConnection connection, T event) {
        this.receivedEvent = event;
        connection.unsubscribeEvent(eventClass, subscriber);
        notifyAll();
    }

}
