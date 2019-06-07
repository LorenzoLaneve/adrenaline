package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

@FunctionalInterface
public interface UserEventSubscriber<T extends UserEvent> {

    void receiveEvent(UserConnection connection, T event);

}
