package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerDisconnectEvent implements UserEvent {

    private PlayerColor color;

    public PlayerDisconnectEvent(PlayerColor color) {
        this.color = color;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
