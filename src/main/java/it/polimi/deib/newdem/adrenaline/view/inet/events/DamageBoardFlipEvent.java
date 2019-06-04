package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class DamageBoardFlipEvent implements UserEvent {

    private PlayerColor color;

    public DamageBoardFlipEvent(PlayerColor color) {
        this.color = color;
    }

    public PlayerColor getColor() {
        return color;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.damageBoardDidFlip(connection, this);
    }

}
