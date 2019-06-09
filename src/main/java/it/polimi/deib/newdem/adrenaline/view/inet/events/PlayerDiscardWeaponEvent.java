package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerDiscardWeaponEvent implements UserEvent {

    private PlayerColor player;

    private int cardID;

    public PlayerDiscardWeaponEvent(PlayerColor player, int cardID) {
        this.player = player;
        this.cardID = cardID;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public int getCardID() {
        return cardID;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
