package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerSelectionEvent implements UserEvent {

    private PlayerColor player;


    public PlayerSelectionEvent(PlayerColor player) {
        this.player = player;
    }

    public PlayerColor getSelectedPlayer() {
        return player;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
