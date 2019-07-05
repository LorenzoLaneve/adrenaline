package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The client responded to the {@code TurnPlayerRequest} with the choice made by the user.
 * @see UserEvent to see what this class is used for.
 */
public class TurnPlayerResponse implements UserEvent {

    private TurnView.ValOrUndo<PlayerColor> player;

    public TurnPlayerResponse(TurnView.ValOrUndo<PlayerColor> player) {
        this.player = player;
    }

    public TurnView.ValOrUndo<PlayerColor> getValue() {
        return player;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
