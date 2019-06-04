package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class GameEndEvent implements UserEvent {





    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.gameWillEnd(connection, this);
    }

}
