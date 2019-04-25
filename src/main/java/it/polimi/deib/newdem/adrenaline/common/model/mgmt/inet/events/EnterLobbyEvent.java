package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionSender;

public class EnterLobbyEvent implements UserEvent {

    private int lobbyIdentifier;


    public EnterLobbyEvent(int lobbyIdentifier) {
        this.lobbyIdentifier = lobbyIdentifier;
    }


    public int getLobbyIdentifier() {
        return lobbyIdentifier;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.serverDidAssignLobby(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendEnterLobbyEvent(this);
    }
}
