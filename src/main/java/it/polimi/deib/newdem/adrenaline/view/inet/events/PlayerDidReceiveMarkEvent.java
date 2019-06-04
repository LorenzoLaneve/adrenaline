package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerDidReceiveMarkEvent implements UserEvent {

    private PlayerColor player;
    private PlayerColor dealer;
    private int mrkAmount;

    public PlayerDidReceiveMarkEvent(PlayerColor player, PlayerColor dealer, int markAmount) {
        this.dealer = dealer;
        this.player = player;
        this.mrkAmount = markAmount;
    }

    public PlayerColor getDealer() {
        return dealer;
    }

    public int getMrkAmount() {
        return mrkAmount;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidReceiveMark(connection, this);
    }

}
