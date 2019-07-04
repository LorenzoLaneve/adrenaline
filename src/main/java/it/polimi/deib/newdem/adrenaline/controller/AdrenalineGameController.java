package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSourceImpl;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.*;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GameController implementation for Adrenaline games handled by the server.
 */
public class AdrenalineGameController implements GameController {

    private Game game;

    private LobbyController lobbyController;

    private VirtualGameView vgv;

    private TimedExecutor currentExecutor;

    private Turn currentTurn;

    /**
     * Initializes the GameController with the LobbyController that hosts the game.
     */
    public AdrenalineGameController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    private Player getPlayer(User user) {
        for (Player player : game.getPlayers()) {
            if (user == game.getUserByPlayer(player)) {
                return player;
            }
        }
        return null;
    }

    private static String getRandomMapID() {
        Random r = new Random();
        int x = r.nextBoolean() ? 1 : 0;
        int y = r.nextBoolean() ? 1 : 0;
        return "Map"+ x +"_"+ y;
    }

    /**
     * Sets up a new game, not restored from disk.
     *
     * @param users soon to be players of the new game
     */
    @Override
    public void setupGame(List<User> users) {
        GameParameters gp = GameParameters.fromConfig(lobbyController.getConfig());
        if(users.isEmpty() || users.size() > lobbyController.getConfig().getMaxPlayers()) {
            throw new IllegalArgumentException();
        }

        String mapID = getRandomMapID();
        Map myMap = Map.createMap("maps/"+ mapID +".json");

        List<ColorUserPair> listCup = generateColorUserOrder(users);

        gp.setGameMap(myMap);
        gp.setColorUserOrder(listCup);
        Config config = lobbyController.getConfig();
        gp.setMinPlayers(config.getMinPlayers());
        gp.setMaxPlayers(config.getMaxPlayers());

        game = new GameImpl(gp);
        // bind map listener?

        vgv = new VirtualGameView();
        game.setGameListener(vgv);

        game.init(); // (VirtualGameView)

        game.getMap().setListener(new VirtualMapView(vgv));

        game.setKillTrackListener(new VirtualKillTrackView(vgv));
        for(Player player : game.getPlayers()) {
            player.setListener(new VirtualPlayerView(vgv, player));
            player.getDamageBoard().setListener(new VirtualDamageBoardView(player, vgv));
            player.getActionBoard().setListener(new VirtualActionBoardView(player, vgv));
        }

        if (Boolean.getBoolean("debugMode")) alterGame(game.getPlayers());

    }

    @Override
    public void recoverGame() {
        // TODO persistency
    }

    private List<ColorUserPair> generateColorUserOrder(List<User> users){
        List<ColorUserPair> listCup = new ArrayList<>(lobbyController.getConfig().getMaxPlayers());

        List<PlayerColor> colors = Arrays.asList(PlayerColor.values());
        Collections.shuffle(colors);
        Collections.shuffle(users);

        for(int i = 0; i < users.size(); i++) {
            listCup.add(new ColorUserPair(colors.get(i), users.get(i)));
        }
        return listCup;
    }

    @Override
    public void runGame() {
        while (!game.isOver()) {
            Turn turn = game.getNextTurn();
            turn.bindDataSource(new TurnDataSourceImpl(new VirtualTurnView(vgv), game));

            if (!turn.getActivePlayer().isConnected()) {
                game.concludeTurn(turn);
                if(!enoughPlayersConnected()) {
                    game.declareOver();
                    break;
                }
                continue;
            }

            synchronized (this) {
                currentTurn = turn;
                currentExecutor = new TimedExecutor(turn::execute);
            }
            try {
                currentExecutor.execute(game.getTurnTime());
            }
            catch (TimeoutException | AbortedException e) {
                // nothing to do here.
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();
            } finally {
                game.concludeTurn(turn);
            }
            synchronized (this) {
                currentTurn = null;
                currentExecutor = null;
            }
        }
        game.concludeGame();
        lobbyController.endGame(); // /snap/
    }

    @Override
    public void userDidDisconnect(User user) {
        Player disconnectedPlayer = getPlayer(user);
        if (disconnectedPlayer != null) {
            vgv.userDidExitGame(user, disconnectedPlayer);

            synchronized (this) {
                if (!enoughPlayersConnected()) {
                    game.declareOver();
                    if (currentTurn != null) currentExecutor.abort();
                } else if (currentTurn != null && currentTurn.getActivePlayer() == disconnectedPlayer) {
                    currentExecutor.abort();
                }
            }

        }
    }

    @Override
    public void userDidReconnect(User user) {
        Player reconnectedPlayer = getPlayer(user);
        if (reconnectedPlayer != null) {
            vgv.userDidEnterGame(user, reconnectedPlayer);
            vgv.playerRestoredMatchData(game, reconnectedPlayer);
        }
    }

    private boolean enoughPlayersConnected() {
        int i = 0;
        for(Player p : game.getPlayers()) {
            if(p.isConnected()) i++;
        }
        return i >= lobbyController.getConfig().getMinPlayers();
    }


    private void alterGame(List<Player> players) {
        List<String> names = players.stream().map(Player::getName).map(String::toLowerCase).collect(Collectors.toList());

        if (names.contains("frenzypls")) {
            for (int i = 0; i < game.getKillTrack().getTrackLength() - 1; i++) {
                game.getKillTrack().addKill(players.get(0), 1);
            }
        }

        /*
        if (names.contains("ezkills")) {
            Player p0 = players.get(0);
            for (Player p : players) if (p != p0) {
                try {
                    for (int j = 0; j < 9; j++) p.getDamageBoard().appendDamage(p0);
                } catch (DamageTrackFullException x) {
                    //nothing to do here.
                }
            }
        }*/

    }

}
