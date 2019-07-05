package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A virtual view is a view object that acts as an adapter between model/controller and views,
 * translating the model objects into plain data objects usable by views.
 * This way the view is completely separated from the model and we do not need to clone/reflect
 * model objects into the client.
 *
 * Note: the VirtualGameView is also used to give information about the in-game users to the other
 * virtual views.
 */
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
    public void addUser(String name) {
        users.add(name);

        if (lobby.isInGame()) {
            for (User user : lobby.getUsers()) {
                if (user.getName().equals(name)) {
                    user.sendEvent(new LobbyDataEvent(users));
                    user.sendEvent(new GameStartEvent());
                    break;
                }
            }
        } else {
            for (User user : lobby.getUsers()) {
                if (user.getName().equals(name)) {
                    user.sendEvent(new LobbyDataEvent(users));
                } else {
                    user.sendEvent(new EnterLobbyEvent(name));
                }
            }
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
    public void startGame() {
        sendEvent(new GameStartEvent());
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

    @Override
    public void lobbyWillStartGame() {
        startGame();
    }

}
