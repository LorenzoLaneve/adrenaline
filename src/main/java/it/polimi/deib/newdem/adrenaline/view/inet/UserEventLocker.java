package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

public class UserEventLocker<T extends UserEvent> {

    private T receivedEvent;

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
