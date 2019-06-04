package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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

}
