package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The game has been restored with the given data.
 * This is used to align the client with the server's model.
 * @see UserEvent to see what this class is used for.
 */
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
