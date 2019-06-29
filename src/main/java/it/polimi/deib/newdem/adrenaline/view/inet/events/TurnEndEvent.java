package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnEndEvent implements UserEvent {

    private PlayerColor actor;

    public TurnEndEvent(PlayerColor actor) {
        this.actor = actor;
    }

    public PlayerColor getTurnActor() {
        return actor;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
