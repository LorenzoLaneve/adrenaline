package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerAcquireWeaponEvent implements UserEvent {

    private PlayerColor color;

    private int cardID;

    public PlayerAcquireWeaponEvent(PlayerColor color, int cardID) {
        this.color = color;
        this.cardID = cardID;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    public int getWeaponCardID() {
        return cardID;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
