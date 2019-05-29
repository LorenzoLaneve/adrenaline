package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class PlayerDidRemoveAmmoSetEvent implements UserEvent {

    private PlayerColor player;
    private int yellowAmount;
    private int redAmount;
    private int blueAmount;

    public PlayerDidRemoveAmmoSetEvent(PlayerColor color, int yellowAmount, int redAmount, int blueAmount) {
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
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidRemoveAmmoSet(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerDidRemoveAmmoSetEvent(this);
    }
}