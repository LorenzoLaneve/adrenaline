package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The kill track in the game registered a new kill from the given player with the given amount of points.
 * @see UserEvent to see what this class is used for.
 */
public class KillTrackAddKillEvent implements UserEvent {

    private final PlayerColor color;
    private final int amount;

    public KillTrackAddKillEvent(PlayerColor color, int amount) {
        this.color = color;
        this.amount = amount;
    }

    public PlayerColor getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
