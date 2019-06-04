package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

import java.util.ArrayList;
import java.util.List;

public class LobbyDataEvent implements UserEvent {

    private List<String> users;

    public LobbyDataEvent(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.lobbyDidSendData(connection, this);
    }

}
