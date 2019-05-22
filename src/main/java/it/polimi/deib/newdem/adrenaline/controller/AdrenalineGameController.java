package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.EnumMap;
import java.util.Map;

public class AdrenalineGameController implements GameController {

    private Game game;

    private LobbyController lobbyController;

    public AdrenalineGameController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    @Override
    public void setupGame() {
        Map<PlayerColor, Player> colorPlayerMap = new EnumMap<>(PlayerColor.class);



        game = new GameImpl(new GameParameters());

        //game

    }

    @Override
    public void start() {
        //TODO
    }

    @Override
    public void recover() {
        //TODO
    }

    public Player getPlayer(User user){
        //TODO
        return null;
    }
}
