package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

/**
 * Interface for subscribers of a {@code UserEvent} in a {@code UserConnection}.
 * @see UserConnection for further information.
 */
@FunctionalInterface
public interface UserEventSubscriber<T extends UserEvent> {

    void receiveEvent(UserConnection connection, T event);

}
