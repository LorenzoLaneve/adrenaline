package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MapBuilder;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class AdrenalineGameController implements GameController {

    private Game game;

    private LobbyController lobbyController;

    public static final int MAX_PLAYERS = 5;

    public AdrenalineGameController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public Player getPlayer(User user){
        //TODO
        return null;
    }

    /**
     * Sets up a new game, not restored from disk.
     *
     * @param users soon to be players of the new game
     */
    @Override
    public void setupGame(List<User> users) {
        //TODO notifica listener
        if(users.isEmpty() || users.size() > MAX_PLAYERS) throw new IllegalArgumentException();

        String s = this.getClass().getClassLoader().getResource("maps/Map0_0.json").getFile().replace("%20", " ");
        Map myMap = Map.createMap( s );
        GameParameters gp = new GameParameters();

        List<ColorUserPair> listCup = new ArrayList<>(MAX_PLAYERS);

        List<PlayerColor> colors = Arrays.asList(PlayerColor.values());
        Collections.shuffle(colors);
        Collections.shuffle(users);

        for(int i = 0; i < users.size(); i++) {
            listCup.add(new ColorUserPair(
                    colors.get(i),
                    users.get(i)
            ));
        }

        gp.setGameMap(myMap);
        gp.setColorUserOrder(listCup);

        game = new GameImpl(gp);
        game.init(); // (VirtualGameView)
        // bind map listener?

        VirtualGameView vgv = new VirtualGameView();

        // game.setKillTrackListener();
    }

    @Override
    public void recoverGame() {

    }

    @Override
    public void runGame() {
        while (!game.isOver()) {
            Turn turn = game.getNextTurn();
            TimedExecutor te = new TimedExecutor(turn::execute);
            try {
                te.execute(game.getTurnTime());
            }
            catch (TimeoutException e) {
                // revert?
            }
        }
        lobbyController.endGame(); // /snap/
    }

    @Override
    public void userDidLeaveLobby(User user) {

    }

    @Override
    public void userDidReenterLobby(User user) {

    }

}
