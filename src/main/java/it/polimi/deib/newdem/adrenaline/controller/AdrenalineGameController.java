package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class AdrenalineGameController implements GameController {

    private Game game;

    private LobbyController lobbyController;

    public AdrenalineGameController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public Player getPlayer(User user){
        //TODO
        return null;
    }

    @Override
    public void setupGame(List<User> users) {

    }

    @Override
    public void recoverGame() {

    }

    @Override
    public void runGame() {

    }

    @Override
    public void userDidLeaveLobby(User user) {

    }

    @Override
    public void userDidReenterLobby(User user) {

    }

}
