package it.polimi.deib.newdem.adrenaline.model.mgmt;


public interface LobbyListener {

    void userDidEnterLobby(User user, Lobby lobby);

    void userDidExitLobby(User user, Lobby lobby);

    void lobbyDidStartTimer(int seconds);

    void lobbyDidSyncTimer(int seconds);

    void lobbyDidAbortTimer();
    
}
