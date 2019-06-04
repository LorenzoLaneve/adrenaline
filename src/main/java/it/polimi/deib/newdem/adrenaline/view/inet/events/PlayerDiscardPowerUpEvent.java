package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerDiscardPowerUpEvent implements UserEvent {

    private PlayerColor color;
    private int cardID;

    public PlayerDiscardPowerUpEvent(PlayerColor color, int cardID) {
        this.color = color;
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public PlayerColor getColor() {
        return color;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidRemovePowerUp(connection, this);

    }

}
