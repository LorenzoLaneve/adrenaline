package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

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
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidTakeDamage(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerDamageEvent(this);
    }
}
