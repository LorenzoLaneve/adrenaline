package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PlayerDataEvent implements UserEvent {

    private PlayerData data;

    public PlayerDataEvent(PlayerData data) {
        this.data = data;
    }

    public PlayerData getData() {
        return data;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
