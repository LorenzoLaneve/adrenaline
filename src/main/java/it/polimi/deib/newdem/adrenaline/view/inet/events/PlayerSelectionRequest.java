package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectionRequest implements UserEvent {

    private List<PlayerColor> selectablePlayers;

    private boolean error;

    public PlayerSelectionRequest(List<PlayerColor> selectablePlayers, boolean error) {
        this.selectablePlayers = selectablePlayers;
        this.error = error;
    }

    public List<PlayerColor> getSelectablePlayers() {
        return new ArrayList<>(selectablePlayers);
    }

    public boolean errorOccurred() {
        return error;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.gameDidRequestPlayer(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerSelectionRequest(this);
    }
}
