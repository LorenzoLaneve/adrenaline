package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player finished their turn.
 * @see UserEvent to see what this class is used for.
 */
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
