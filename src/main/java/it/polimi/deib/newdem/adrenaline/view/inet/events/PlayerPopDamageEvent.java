package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerPopDamageEvent implements UserEvent {

    private PlayerColor color;

    public PlayerPopDamageEvent(PlayerColor color) {
        this.color = color;
    }

    public PlayerColor getColor() {
        return color;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}