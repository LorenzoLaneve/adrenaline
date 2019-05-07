package it.polimi.deib.newdem.adrenaline.model.mgmt;

public class InGameLobbyState implements LobbyState {

    InGameLobbyState() {
        // nothing to do here
    }

    @Override
    public LobbyState userDidEnterLobby(User user, Lobby lobby) {
        return this;
    }

    @Override
    public LobbyState userDidExitLobby(User user, Lobby lobby) {
        return this;
    }

    @Override
    public void lobbyWillEnterState(Lobby lobby) {
        // TODO
    }

    @Override
    public void lobbyDidExitState(Lobby lobby) {
        // TODO
    }

}
