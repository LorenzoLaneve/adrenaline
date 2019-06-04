package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.ArrayList;
import java.util.List;


public class VirtualLobbyView implements LobbyView, LobbyListener {

    private Lobby lobby;

    private List<String> users;

    public VirtualLobbyView(Lobby lobby) {
        this.lobby = lobby;

        this.users = new ArrayList<>();
    }


    private void sendEvent(UserEvent event) {
        for (User user : lobby.getUsers()) {
            user.sendEvent(event);
        }
    }


    @Override
    public void addGameView(GameView gv) {
        // TODO
    }

    @Override
    public void addUser(String name) {
        sendEvent(new EnterLobbyEvent(name));

        users.add(name);

        for (User user : lobby.getUsers()) if (user.getName().equals(name)) {
            user.sendEvent(new LobbyDataEvent(users));
            break;
        }

    }

    @Override
    public void removeUser(String name) {
        sendEvent(new ExitLobbyEvent(name));
        users.remove(name);
    }

    @Override
    public void startTimer(int seconds) {
        sendEvent(new LobbyTimerStartEvent(seconds));
    }

    @Override
    public void syncTimer(int seconds) {
        sendEvent(new LobbyTimerUpdateEvent(seconds));
    }

    @Override
    public void abortTimer() {
        sendEvent(new LobbyTimerAbortEvent());
    }


    @Override
    public void userDidEnterLobby(User user, Lobby lobby) {
        addUser(user.getName());
    }

    @Override
    public void userDidExitLobby(User user, Lobby lobby) {
        removeUser(user.getName());
    }

    @Override
    public void lobbyDidStartTimer(int seconds) {
        startTimer(seconds);
    }

    @Override
    public void lobbyDidSyncTimer(int seconds) {
        syncTimer(seconds);
    }

    @Override
    public void lobbyDidAbortTimer() {
        abortTimer();
    }

}
