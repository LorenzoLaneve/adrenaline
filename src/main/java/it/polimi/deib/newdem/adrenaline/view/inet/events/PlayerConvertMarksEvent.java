package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class PlayerConvertMarksEvent implements UserEvent {

    private PlayerColor damagedPlayer;

    public PlayerConvertMarksEvent(PlayerColor damagedPlayer) {
        this.damagedPlayer = damagedPlayer;
    }

    public PlayerColor getDamagedPlayer() {
        return damagedPlayer;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidConvertMarks(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerConvertMarksEvent(this);
    }
}
