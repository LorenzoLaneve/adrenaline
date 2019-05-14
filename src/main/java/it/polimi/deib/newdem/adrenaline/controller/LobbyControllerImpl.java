package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.*;
import it.polimi.deib.newdem.adrenaline.view.server.LobbyViewEventListener;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualLobbyView;

public class LobbyControllerImpl implements LobbyController, LobbyViewEventListener {

    private Thread mainThread;
    private LobbyListener listener;
    private Lobby lobby;
    private VirtualLobbyView view;

    public LobbyControllerImpl(Config config) {
        this.lobby = new LobbyImpl(config.getMinPlayers(), config.getMaxPlayers(), GameManagerFactory.create());

        this.view = new VirtualLobbyView(lobby, this);
        this.lobby.setListener(view);
    }

    @Override
    public Lobby getLobby() {
        return lobby;
    }

    @Override
    public void addUser(User user) {
        this.lobby.addUser(user);
    }

    @Override
    public void removeUser(User user) {
        this.lobby.removeUser(user);
    }

    @Override
    public void userDidLoadLobby(User user, Lobby lobby) {

    }

    @Override
    public void userWillExitLobby(User user, Lobby lobby) {

    }

}
