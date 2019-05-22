package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class GameEndEvent implements UserEvent {





    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.gameWillEnd(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendGameEndEvent(this);
    }

}
