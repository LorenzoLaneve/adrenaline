package it.polimi.deib.newdem.adrenaline.model.mgmt;

public class ReadyLobbyState implements LobbyState {

    ReadyLobbyState() {
        // nothing to do here
    }

    @Override
    public LobbyState userDidEnterLobby(User user, Lobby lobby) {
        int usersCount = lobby.getUsers().size();

        if (usersCount >= lobby.getMaxPlayers()) {
            return new InGameLobbyState();
        }

        if (lobby.getMinPlayers() <= usersCount) {
            return new TimerWaitingLobbyState();
        }

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
