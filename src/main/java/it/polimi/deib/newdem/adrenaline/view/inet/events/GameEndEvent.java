package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class GameEndEvent implements UserEvent {

    private GameResults results;

    public GameEndEvent(GameResults results) {
        this.results = results;
    }

    public GameResults getResults() {
        return results;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
