package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class ReadyLobbyState implements LobbyState {

    ReadyLobbyState() {
        // nothing to do here
    }

    @Override
    public LobbyState userDidEnterLobby(User user, LobbyController lobbyController) {
        Lobby lobby = lobbyController.getLobby();

        int usersCount = lobby.getUsers().size();

        if (usersCount >= lobbyController.getMaxPlayers()) {
            return new InGameLobbyState();
        }

        if (lobbyController.getMinPlayers() <= usersCount) {
            return new TimerWaitingLobbyState();
        }

        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, LobbyController lobbyController) {
        return this;
    }

    @Override
    public void lobbyWillEnterState(LobbyController lobbyController) {
        // TODO
    }

    @Override
    public void lobbyDidExitState(LobbyController lobbyController) {
        // TODO
    }

    @Override
    public boolean acceptsNewUsers() {
        return true;
    }

}
