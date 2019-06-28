package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

/**
 * Lobby state that represents a lobby hosting an ongoing game.
 * No new users are accepted and new users cannot modify the lobby state.
 */
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
        // nothing to do here
    }

    @Override
    public boolean acceptsNewUsers() {
        return false;
    }

}
