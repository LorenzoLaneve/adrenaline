package it.polimi.deib.newdem.adrenaline.common.model.mgmt;


public interface LobbyListener {

    void userDidEnterLobby(User user, Lobby lobby);

    void userDidExitLobby(User user, Lobby lobby);
}
