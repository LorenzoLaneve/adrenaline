package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class InGameLobbyState implements LobbyState {

    InGameLobbyState() {
        // nothing to do here
    }

    @Override
    public LobbyState userDidEnterLobby(User user, LobbyController lobbyController) {
        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, LobbyController lobbyController) {
        return this;
    }

    @Override
    public void lobbyWillEnterState(LobbyController lobbyController) {
        lobbyController.startGame();
    }

    @Override
    public void lobbyDidExitState(LobbyController lobbyController) {
        // TODO
    }

    @Override
    public boolean acceptsNewUsers() {
        return false;
    }

}
