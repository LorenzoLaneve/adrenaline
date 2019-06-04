package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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

}
