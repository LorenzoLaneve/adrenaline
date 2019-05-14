package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.controller.GameManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class LobbyImpl implements Lobby {

    private int minPlayers;

    private int maxPlayers;

    private List<User> users;

    private LobbyState lobbyState;

    private GameManagerFactory gameManagerFactory;

    private LobbyListener listener;

    /**
     * Creates a new and empty lobby.
     * @param minPlayers The minimum players required to start.
     * @param maxPlayers The number of players that will cause the lobby to immediately start the game.
     * @param gmf A GameManagerFactory object that will create the manager for the game hosted by the lobby.
     */
    public LobbyImpl(int minPlayers, int maxPlayers, GameManagerFactory gmf){
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;

        this.users = new ArrayList<>();

        this.gameManagerFactory = gmf;

        this.switchState(new ReadyLobbyState());
    }

    private synchronized void switchState(LobbyState newState) {
        if (newState != this.lobbyState) {
            if (lobbyState != null) {
                lobbyState.lobbyDidExitState(this);
            }

            newState.lobbyWillEnterState(this);
        }

        this.lobbyState = newState;
    }

    @Override
    public void setListener(LobbyListener listener) {
        this.listener = listener;
    }

    @Override
    public synchronized void addUser(User user) {
        this.users.add(user);

        this.switchState(lobbyState.userDidEnterLobby(user, this));
    }

    @Override
    public synchronized void removeUser(User user) {
        this.users.remove(user);

        this.switchState(lobbyState.userDidExitLobby(user, this));
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public synchronized boolean acceptsNewUsers() {
        return lobbyState.acceptsNewUsers();
    }

}
