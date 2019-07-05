package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player received ammos for the given amount.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerReceiveAmmoEvent implements UserEvent {

    private PlayerColor player;
    private int yellowAmount;
    private int redAmount;
    private int blueAmount;

    public PlayerReceiveAmmoEvent(PlayerColor player, int yellowAmount, int redAmount, int blueAmount) {
        this.player = player;
        this.yellowAmount = yellowAmount;
        this.redAmount = redAmount;
        this.blueAmount = blueAmount;
    }

    public PlayerColor getPlayer() {
        return player;
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
