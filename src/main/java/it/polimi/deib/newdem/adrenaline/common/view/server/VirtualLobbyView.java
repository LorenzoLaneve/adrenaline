package it.polimi.deib.newdem.adrenaline.common.view.server;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.common.view.GameView;
import it.polimi.deib.newdem.adrenaline.common.view.LobbyView;

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
