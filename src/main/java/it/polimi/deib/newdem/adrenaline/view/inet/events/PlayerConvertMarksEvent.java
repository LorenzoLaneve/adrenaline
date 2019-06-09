package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerConvertMarksEvent implements UserEvent {

    private PlayerColor damagedPlayer;

    public PlayerConvertMarksEvent(PlayerColor damagedPlayer) {
        this.damagedPlayer = damagedPlayer;
    }

    public PlayerColor getDamagedPlayer() {
        return damagedPlayer;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
