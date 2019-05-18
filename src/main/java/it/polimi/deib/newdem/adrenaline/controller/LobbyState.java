package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface LobbyState {

    LobbyState userDidEnterLobby(User user, LobbyController lobby);

    LobbyState userDidExitLobby(User user, LobbyController lobby);

    void lobbyWillEnterState(LobbyController lobby);

    void lobbyDidExitState(LobbyController lobby);

    boolean acceptsNewUsers();
}
