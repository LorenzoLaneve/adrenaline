package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class TimerWaitingLobbyState implements LobbyState {

    private Timer timer;

    TimerWaitingLobbyState() {
        // nothing to do
    }

    @Override
    public LobbyState userDidEnterLobby(User user, LobbyController lobbyController) {
        Lobby lobby = lobbyController.getLobby();

        if (lobby.getUsers().size() > lobbyController.getConfig().getMaxPlayers()) {
            return new InGameLobbyState();
        }

        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, LobbyController lobbyController) {
        Lobby lobby = lobbyController.getLobby();

        if (lobby.getUsers().size() < lobbyController.getConfig().getMinPlayers()) {
            return new ReadyLobbyState();
        }

        return this;
    }

    @Override
    public void lobbyWillEnterState(LobbyController lobbyController) {
        timer = new Timer(1, lobbyController.getTimerListener());
        timer.start(lobbyController.getConfig().getTimerLength());
    }

    @Override
    public void lobbyDidExitState(LobbyController lobbyController) {
        if (timer.isOver() && !timer.isOver()) {
            timer.abort();
        }
    }

    @Override
    public boolean acceptsNewUsers() {
        return true;
    }

}
