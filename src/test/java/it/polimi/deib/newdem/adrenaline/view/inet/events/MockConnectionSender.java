package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventSubscriber;

import java.util.List;
import java.util.Map;

public class MockConnectionSender implements UserConnection {
    @Override
    public void start() {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public <T extends UserEvent> void subscribeEvent(Class<T> eventClass, UserEventSubscriber<T> subscriber) {

    }

    @Override
    public <T extends UserEvent> void unsubscribeEvent(Class<T> eventClass, UserEventSubscriber<T> subscriber) {

    }

    @Override
    public void clearSubscribers() {

    }

    @Override
    public void copySubscribers(Map<Class<? extends UserEvent>, List<UserEventSubscriber>> subscribers) {

    }


    @Override
    public void sendEvent(UserEvent event) {

    }

    @Override
    public <T extends UserEvent> void publishEvent(T event) {

    }

    @Override
    public void close() {

    }
}
