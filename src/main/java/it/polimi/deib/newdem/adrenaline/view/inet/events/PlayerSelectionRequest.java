package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectionRequest implements UserEvent {

    private List<PlayerColor> selectablePlayers;

    private boolean mandatory;

    public PlayerSelectionRequest(List<PlayerColor> selectablePlayers, boolean mandatory) {
        this.selectablePlayers = selectablePlayers;
        this.mandatory = mandatory;
    }

    public List<PlayerColor> getSelectablePlayers() {
        return new ArrayList<>(selectablePlayers);
    }

    public boolean isMandatory() {
        return mandatory;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.gameDidRequestPlayer(connection, this);
    }

}
