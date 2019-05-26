package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

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

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerDidReceiveMarkEvent(this);
    }
}
