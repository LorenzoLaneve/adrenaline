package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class LeaveMapPlayerEvent implements UserEvent {

    PlayerColor playerColor;

    public LeaveMapPlayerEvent(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
