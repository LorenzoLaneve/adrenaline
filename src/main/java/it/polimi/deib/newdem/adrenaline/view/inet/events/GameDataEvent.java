package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class GameDataEvent implements UserEvent {

    private GameData data;

    public GameDataEvent(GameData data) {
        this.data = data;
    }

    public GameData getData() {
        return data;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
