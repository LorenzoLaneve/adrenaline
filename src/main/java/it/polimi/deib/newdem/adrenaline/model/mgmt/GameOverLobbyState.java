package it.polimi.deib.newdem.adrenaline.model.mgmt;

public class GameOverLobbyState implements LobbyState {

    GameOverLobbyState() {
        // TODO
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

    @Override
    public boolean acceptsNewUsers() {
        return false;
    }

}
