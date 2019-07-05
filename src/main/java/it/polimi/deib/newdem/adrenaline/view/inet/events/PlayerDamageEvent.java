package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The first player took damage from the second player, for the specified amount.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerDamageEvent implements UserEvent {

    private PlayerColor damagedPlayer;

    private PlayerColor attacker;

    private int damageAmount;

    private int markAmount;


    public PlayerDamageEvent(PlayerColor damagedPlayer, PlayerColor dealer, int damageAmount, int markAmount) {
        this.damagedPlayer = damagedPlayer;
        this.attacker = dealer;
        this.damageAmount = damageAmount;
        this.markAmount = markAmount;
    }

    public PlayerColor getDamagedPlayer() {
        return damagedPlayer;
    }

    public PlayerColor getAttacker() {
        return attacker;
    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public int getMarkAmount() {
        return markAmount;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
