package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class DeathPlayerEvent implements UserEvent {

    PlayerColor playerColor;

    public DeathPlayerEvent(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidDie(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendDeathPlayerEvent(this);
    }
}
