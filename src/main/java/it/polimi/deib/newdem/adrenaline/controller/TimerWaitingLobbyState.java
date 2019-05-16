package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class TimerWaitingLobbyState implements LobbyState {

    private LobbyTimer timer;

    TimerWaitingLobbyState() {
        // nothing to do
    }

    @Override
    public LobbyState userDidEnterLobby(User user, LobbyController lobbyController) {
        Lobby lobby = lobbyController.getLobby();

        if (lobby.getUsers().size() > lobbyController.getMaxPlayers()) {
            return new InGameLobbyState();
        }

        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, LobbyController lobbyController) {
        Lobby lobby = lobbyController.getLobby();

        if (lobby.getUsers().size() < lobbyController.getMinPlayers()) {
            return new ReadyLobbyState();
        }

        return this;
    }

    @Override
    public void lobbyWillEnterState(LobbyController lobbyController) {
        timer = new LobbyTimer(10, lobbyController.getTimerListener());
        timer.start(60);
        // FIXME the sync period should be retrieved from the config.
    }

    @Override
    public void lobbyDidExitState(LobbyController lobbyController) {
        if (!timer.abortRequested()) {
            timer.abort();
        }
    }

    @Override
    public boolean acceptsNewUsers() {
        return true;
    }

}
