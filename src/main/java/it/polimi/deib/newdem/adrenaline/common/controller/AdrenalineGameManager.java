package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

import java.util.Map;

public class AdrenalineGameManager implements GameManager {

    private Game game;
    private Map<User, Player> players;

    @Override
    public void setupGame() {
        //TODO
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
