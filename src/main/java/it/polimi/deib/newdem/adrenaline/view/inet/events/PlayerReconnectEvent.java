package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player reconnected to the game.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerReconnectEvent implements UserEvent {

    private PlayerColor color;

    public PlayerReconnectEvent(PlayerColor color) {
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
