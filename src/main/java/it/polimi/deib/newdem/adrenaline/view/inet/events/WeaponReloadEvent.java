package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player reloaded the weapon with the given card ID, according to the user weapon deck.
 * @see UserEvent to see what this class is used for.
 */
public class WeaponReloadEvent implements UserEvent {

    private PlayerColor color;
    private int cardID;

    public WeaponReloadEvent(PlayerColor color, int cardID) {
        this.color = color;
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public PlayerColor getOwnerColor() {
        return color;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }
}
