package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

/**
 * Lobby state that represents a lobby with a game that fully finished its life cycle.
 * This does not accept new users, and does not allow any new user to modify the state of the lobby.
 */
public class GameOverLobbyState implements LobbyState {

    GameOverLobbyState() {
        // nothing to init.
    }

    @Override
    public LobbyState userDidEnterLobby(User user, LobbyController lobby) {
        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, LobbyController lobby) {
        return this;
    }

    @Override
    public void lobbyWillEnterState(LobbyController lobby) {
        // nothing to do here.
    }

    @Override
    public void lobbyDidExitState(LobbyController lobby) {
        // nothing to do here.
    }

    @Override
    public boolean acceptsNewUsers() {
        return false;
    }

}
