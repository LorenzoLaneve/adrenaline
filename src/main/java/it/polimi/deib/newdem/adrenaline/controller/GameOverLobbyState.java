package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class GameOverLobbyState implements LobbyState {

    GameOverLobbyState() {
        // TODO
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
        // TODO
    }

    @Override
    public void lobbyDidExitState(LobbyController lobby) {
        // TODO
    }

    @Override
    public boolean acceptsNewUsers() {
        return false;
    }

}
