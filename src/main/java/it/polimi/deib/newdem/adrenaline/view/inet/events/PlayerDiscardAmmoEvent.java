package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player discarded the given amount of ammos.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerDiscardAmmoEvent implements UserEvent {

    private PlayerColor color;
    private int yellowAmount;
    private int redAmount;
    private int blueAmount;

    public PlayerDiscardAmmoEvent(PlayerColor color, int yellowAmount, int redAmount, int blueAmount) {
        this.color = color;
        this.yellowAmount = yellowAmount;
        this.redAmount = redAmount;
        this.blueAmount = blueAmount;
    }

    public PlayerColor getPlayer() {
        return color;
    }

    public int getBlueAmount() {
        return blueAmount;
    }

    public int getRedAmount() {
        return redAmount;
    }

    public int getYellowAmount() {
        return yellowAmount;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
