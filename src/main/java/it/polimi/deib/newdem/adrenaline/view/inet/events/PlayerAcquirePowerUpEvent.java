package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerAcquirePowerUpEvent implements UserEvent {

    private PlayerColor color;

    private int cardID;

    public PlayerAcquirePowerUpEvent(PlayerColor color, int cardID) {
        this.color = color;
        this.cardID = cardID;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    public int getPowerUpCardID() {
        return cardID;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidAcquirePowerUp(connection, this);
    }

}
