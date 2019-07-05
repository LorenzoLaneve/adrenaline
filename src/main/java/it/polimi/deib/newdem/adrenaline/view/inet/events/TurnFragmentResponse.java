package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The client responded to the {@code TurnFragmentRequest} with the choice made by the user.
 * @see UserEvent to see what this class is used for.
 */
public class TurnFragmentResponse implements UserEvent {

    private TurnView.ValOrUndo<Integer> frag;

    public TurnFragmentResponse(TurnView.ValOrUndo<Integer> frag) {
        this.frag = frag;
    }

    public TurnView.ValOrUndo<Integer> getValue() {
        return frag;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
