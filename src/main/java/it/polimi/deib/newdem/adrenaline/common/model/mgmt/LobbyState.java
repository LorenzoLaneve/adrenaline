package it.polimi.deib.newdem.adrenaline.common.model.mgmt;

public interface LobbyState {

    LobbyState userDidEnterLobby(User user, Lobby lobby);

    LobbyState userDidExitLobby(User user, Lobby lobby);

    void lobbyWillEnterState(Lobby lobby);

    void lobbyDidExitState(Lobby lobby);
}
