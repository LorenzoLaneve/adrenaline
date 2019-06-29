package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class TurnFragmentRequest implements UserEvent {

    private ArrayList<Integer> fragments;
    private boolean forceChoice;

    public TurnFragmentRequest(List<Integer> fragments, boolean forceChoice) {
        this.fragments = new ArrayList<>(fragments);
        this.forceChoice = forceChoice;
    }

    public List<Integer> getSelectableFragments() {
        return fragments;
    }

    public boolean isChoiceForced() {
        return forceChoice;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
