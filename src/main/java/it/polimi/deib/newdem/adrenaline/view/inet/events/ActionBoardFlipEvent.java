package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The action board of the given player flipped to frenzy mode.
 * @see UserEvent to see what this class is used for.
 */
public class ActionBoardFlipEvent implements UserEvent {

    private PlayerColor color;

    public ActionBoardFlipEvent(PlayerColor color) {
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
