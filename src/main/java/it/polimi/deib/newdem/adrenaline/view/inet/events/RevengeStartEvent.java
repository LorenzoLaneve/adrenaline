package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player started its revenge sub-turn.
 * @see UserEvent to see what this class is used for.
 */
public class RevengeStartEvent implements UserEvent {

    private PlayerColor actor;

    public RevengeStartEvent(PlayerColor actor) {
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
