package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerDidReceiveDamageEvent implements UserEvent {

    private PlayerColor target;
    private PlayerColor dealer;
    private int dmgAmount;


    public PlayerDidReceiveDamageEvent(PlayerColor target, PlayerColor dealer, int dmgAmount) {
        this.target = target;
        this.dealer = dealer;
        this.dmgAmount = dmgAmount;
    }

    public int getDmgAmount() {
        return dmgAmount;
    }

    public PlayerColor getDealer() {
        return dealer;
    }

    public PlayerColor getTarget() {
        return target;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidReceiveDamage(connection, this);
    }

}
