package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class RevengeEndEvent implements UserEvent {

    private PlayerColor actor;

    public RevengeEndEvent(PlayerColor actor) {
        this.actor = actor;
    }

    public PlayerColor getActor() {
        return actor;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
