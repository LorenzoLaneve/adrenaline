package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class KillTrackAddKillEvent implements UserEvent {

    private final PlayerColor color;
    private final int amount;

    public KillTrackAddKillEvent(PlayerColor color, int amount) {
        this.color = color;
        this.amount = amount;
    }

    public PlayerColor getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.killTrackDidAddTear(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendKillTrackAddKillEvent(this);
    }
}
