package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.*;
import it.polimi.deib.newdem.adrenaline.view.server.LobbyViewEventListener;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualLobbyView;

public class LobbyControllerImpl implements LobbyController, LobbyViewEventListener, LobbyTimerListener {

    private Thread mainThread;

    private Lobby lobby;

    private LobbyState lobbyState;

    private int minPlayers;
    private int maxPlayers;

    LobbyControllerImpl(Config config) {
        this.lobby = new LobbyImpl();

        this.minPlayers = config.getMinPlayers();
        this.maxPlayers = config.getMaxPlayers();

        VirtualLobbyView view = new VirtualLobbyView(lobby, this);
        this.lobby.setListener(view);

        this.switchState(new ReadyLobbyState());
    }

    private synchronized void switchState(LobbyState newState) {
        if (newState != this.lobbyState) {
            if (lobbyState != null) {
                lobbyState.lobbyDidExitState(this);
            }

            newState.lobbyWillEnterState(this);
        }

        this.lobbyState = newState;
    }

    @Override
    public Lobby getLobby() {
        return lobby;
    }

    @Override
    public synchronized void addUser(User user) {
        this.lobby.addUser(user);

        this.switchState(lobbyState.userDidEnterLobby(user, this));
    }

    @Override
    public synchronized void removeUser(User user) {
        this.lobby.removeUser(user);

        this.switchState(lobbyState.userDidExitLobby(user, this));
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public LobbyTimerListener getTimerListener() {
        return this;
    }

    @Override
    public synchronized boolean acceptsNewUsers() {
        return lobbyState.acceptsNewUsers();
    }

    @Override
    public void userDidLoadLobby(User user, Lobby lobby) {
        // TODO
    }

    @Override
    public void userWillExitLobby(User user, Lobby lobby) {
        // TODO
    }


    @Override
    public void timerWillStart(int secondsLeft) {
        lobby.startTimer(secondsLeft);
    }

    @Override
    public void timerSync(int secondsLeft) {
        lobby.refreshTimer(secondsLeft);
    }

    @Override
    public void timerDidFinish() {
        //lobby.abortTimer();
    }

    @Override
    public void timerDidAbort() {
        lobby.abortTimer();
    }
}
