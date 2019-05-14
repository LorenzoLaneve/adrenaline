package it.polimi.deib.newdem.adrenaline.model.mgmt;

public class TimerWaitingLobbyState implements LobbyState {

    TimerWaitingLobbyState() {
        //TODO
    }

    @Override
    public LobbyState userDidEnterLobby(User user, Lobby lobby) {
        // TODO
        return null;
    }

    @Override
    public LobbyState userDidExitLobby(User user, Lobby lobby) {
        // TODO
        return null;
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
        return true;
    }

}
