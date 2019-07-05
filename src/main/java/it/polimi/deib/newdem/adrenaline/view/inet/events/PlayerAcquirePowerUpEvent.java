package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player acquired the power up in the given ID, according to the used power up deck.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerAcquirePowerUpEvent implements UserEvent {

    private PlayerColor color;

    private int cardID;

    public PlayerAcquirePowerUpEvent(PlayerColor color, int cardID) {
        this.color = color;
        this.cardID = cardID;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    public int getPowerUpCardID() {
        return cardID;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
