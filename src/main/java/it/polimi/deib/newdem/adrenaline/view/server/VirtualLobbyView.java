package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;

public class VirtualLobbyView implements LobbyView, LobbyListener {


    @Override
    public void userDidEnterLobby(User user, Lobby lobby) {
        // TODO
    }

    @Override
    public void userDidExitLobby(User user, Lobby lobby) {
        // TODO
    }

    @Override
    public void addGameView(GameView gv) {
        // TODO
    }

    @Override
    public void addUser(String name) {
        // TODO
    }

    @Override
    public void startTimer(int seconds) {
        // TODO
    }

    @Override
    public void syncTimer(int seconds) {
        // TODO
    }

}
