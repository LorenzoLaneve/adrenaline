package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The damage board of the given player was cleared.
 * @see UserEvent to see what this class is used for.
 */
public class DamageBoardClearEvent implements UserEvent {

    private PlayerColor color;

    public DamageBoardClearEvent(PlayerColor color) {
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
