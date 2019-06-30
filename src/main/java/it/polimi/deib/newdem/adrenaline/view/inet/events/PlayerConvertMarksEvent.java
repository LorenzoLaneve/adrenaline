package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerConvertMarksEvent implements UserEvent {

    private PlayerColor damagedPlayer;

    private PlayerColor dealer;

    public PlayerConvertMarksEvent(PlayerColor damagedPlayer, PlayerColor dealer) {
        this.damagedPlayer = damagedPlayer;
        this.dealer = dealer;
    }

    public PlayerColor getDamagedPlayer() {
        return damagedPlayer;
    }

    public PlayerColor getDealer() {
        return dealer;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
