package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoard;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardImpl;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrack;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackImpl;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.game.turn.FirstTurn;
import it.polimi.deib.newdem.adrenaline.model.game.turn.OrdinaryTurn;
import it.polimi.deib.newdem.adrenaline.model.game.turn.RoundRobin;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualPlayerView;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;
import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.OVERKILL_SHOT_INDEX;

public class GameImpl implements Game {

    private Map map;
    private RoundRobin<Turn> turnQueue;
    private KillTrack killTrack;
    private List<Player> players;
    private boolean isFrenzy; // BAD, enum or states
    private boolean isOver;
    private int turnTime;
    private GameListener listener;
    private java.util.Map<PlayerColor, User> colorUserMap;
    private boolean init;
    private ListenerRegistry listenerRegistry;

    public static final int MAX_PLAYERS_PER_GAME = 5;

    /**
     * Builds a new game from the given {@code GameParameters}
     *
     * @param parameters to build the new game from
     */
    public GameImpl(GameParameters parameters) {
        map = parameters.getGameMap();
        killTrack = new KillTrackImpl(parameters.getKillTrackInitialLength());
        colorUserMap = parameters.getColorUserMap();
        players = new ArrayList<>(MAX_PLAYERS_PER_GAME);
        // ^ no players added
        turnQueue = new RoundRobin<>();
        isOver = false;
        turnTime = parameters.getTurnTime();
        listenerRegistry = new ListenerRegistry();
        init = false;
    }

    private GameImpl(){}
/*
    public static Game fromData(GameData gameData) {
        GameImpl game = new GameImpl();
        game.map = gameData.getMap();
        game.map.setListener(null);
        game.map.addDrops();

        game.weaponDeck = new WeaponDeck<WeaponCard>(gameData.getWeaponDeckCards());
        game.powerUpDeck = new PowerUpDeck<PowerUpCard>(gameData.getPowerUpDeckCards());
        //^ creates decks with all cards
        // discarding is rough
        // actives are also rough
        // order is also rough

        for(PlayerData playerData : gameData.getPlayersDataList()){
            game.players.add( PlayerImpl.fromDataEmptyBoards(playerData, game) );
            // yes color
        }

        for(PlayerData playerData : gameData.getPlayersDataList()) {
            Player currentplayer = game.getPlayerFromColor(playerData.getColor());
            DamageBoardData dmgbd = currentData.getDamageBoardData();

            if(dmgbd.isFlipped()) {
                boolean precedesFirstPlayer =
                        playerData.getActionBoardData().getFace() == FrenzyDouble;
                currentplayer.goFrenzy(precedesFirstPlayer);
            }

            for(PlayerColor damager : dmgbd.getDamages()) {
                currentplayer.takeDamage(1, game.getPlayerFromColor(damager));
            }

            for(java.util.Map.Entry<PlayerColor,Integer> entry : dmgdb.getMarks().entrySet()){
                currentplayer.takeMark(
                        entry.getValue(),
                        game.getPlayerFromColor(entry.getKey()));
            }

        }

        //illTrack = new KillTrackImpl(parameters.getKillTrackInitialLength());
        game.killTrack = gameData.getKillTrack();
        //colorUserMap = parameters.getColorUserMap();
        //players = new ArrayList<>(MAX_PLAYERS_PER_GAME);
        //// ^ no players added
        game.players = new ArrayList<>(MAX_PLAYERS_PER_GAME);



        //turnQueue = new RoundRobin<>();
        //isOver = false;
        //turnTime = parameters.getTurnTime();
        //listenerRegistry = new ListenerRegistry();
        //init = false;
    }
*/
    /**
     * Returns a reference to tis game's map
     *
     * @return reference to this game's map
     */
    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public void setGameListener(GameListener listener) {
        if(init) this.listener = listener;
        else listenerRegistry.setGameListener(listener);
    }

    /**
     * Identifies a {@code Player} in this game from its {@code PlayerColor}
     *
     * @param color of the desired {@code Player}
     * @return player of the given color in this game.
     * Returns null if no player is present for the given color.
     */
    @Override
    public Player getPlayerFromColor(PlayerColor color) {
        for(Player p : players) {
            if(color == p.getColor()){
                return p;
            }
        }
        return null;
    }

    /**
     * Checks if this game's state is frenzy.
     *
     * @return is in frenzy
     */
    @Override
    public boolean isInFrenzy() {
        return isFrenzy;
    }

    /**
     * Checks if this game meets the criteria to go to the Frenzy stage.
     *
     * @return should this game go Frenzy
     */
    @Override
    public boolean shouldGoFrenzy() {
        return !isFrenzy &&
                killTrack.getTotalKills() > killTrack.getTrackLength();
    }

    private void bindElementsListeners() {
        // attach new listeners
        killTrack.setListener(listenerRegistry.getKillTrackListener());
        listener = listenerRegistry.getGameListener();
    }

    private void createNewPlayers(){
        if(colorUserMap.isEmpty()) {
            throw new IllegalStateException();
        }
        if(!players.isEmpty()) throw new IllegalStateException();

        for(java.util.Map.Entry<PlayerColor,User> e : colorUserMap.entrySet()) {
            Player newPlayer = new PlayerImpl(
                    e.getKey(),
                    this,
                    e.getValue().getName()
            );
            // newPlayer.setListener(new VirtualPlayerView(listener));
            // should be in controller
            // should send a copy of its current state, whichever it is
            // @persistence
            players.add(newPlayer);
        }
    }

    private void notifyPlayersIngress(){
        for(Player p : players) {
            listener.userDidEnterGame(
                    colorUserMap.get(p.getColor()),
                    p);
        }
    }

    private void setUpRoundRobin() {
        if(players.isEmpty()) {
            throw new IllegalStateException();
        }
        for (Player p : players) {
            // p.init();
            turnQueue.enqueue(new FirstTurn(p));
        }
    }

    private void initPlayers() {
        if(players.isEmpty()) {
            throw new IllegalStateException();
        }
        for (Player p : players) {
            p.init();
        }
    }

    /**
     * Prepares this game for its first execution
     */
    public void init() {
        // TODO check that listener is not null
        // TODO add flavorful exception

        bindElementsListeners();

        // load cards(?)

        // create Players
        createNewPlayers();

        // register players on listener
        notifyPlayersIngress();

        // prepare round robin
        setUpRoundRobin();

        // init players
        initPlayers();

        // set flags
        isFrenzy = false;
        init = true;
    }

    /**
     * Returns the next turn.
     *
     * @return
     */
    @Override
    public Turn getNextTurn() {
        Turn t = turnQueue.next();
        if(null == t) isOver = true;
        return t;
    }

    /**
     * Performs actions at the end of a turn.
     *
     * Among them, passing to frenzy if needed.
     *
     * @param turn to conclude
     */
    @Override
    public void concludeTurn(Turn turn) {
        //TODO
        // EOT actions

        // extra point for multiple kills
        for(Player p :players) {
            int killsCount = 0;
            for(Player q : players) {
                Player d = q.getDamager(DEATH_SHOT_INDEX);
                if(null != d && d.equals(p)) {
                    killsCount++;
                }
                if(killsCount >= 2) {
                    p.addScore(1);
                }
            }
        }

        // register kills
        for(Player p : players) {
            if(p.isDead()) {
                distributeScore(p); // assigns the due score to damagers
                registerDeath(p);   // updates killtrack

                //TODO
                // remove player from map
                // setup respawn (if not implicit in turn)
            }
        }

        // add new turn
        if(!isFrenzy) {
            turnQueue.enqueue(new OrdinaryTurn(turn.getActivePlayer()));
        }

        //go to frenzy if required
        if(shouldGoFrenzy()) {
            goFrenzy();
        }
    }

    /**
     * Assigns a new {@code User} to the {@code Player} of the given {@code PlayerColor}.
     *
     * @throws IllegalArgumentException if there is no player for the given {@code PlayerColor}
     * @param user to assign to the player of the given color
     * @param color of the user to whom assign the new user-
     */
    @Override
    public void setUserForColor(User user, PlayerColor color) {
        if(!colorUserMap.containsKey(color)) throw new IllegalArgumentException();
        colorUserMap.replace(color, user);
    }

    /**
     * Gets the {@code User} associated with this {@code Player}
     *
     * @param player to find the user associated with
     * @return user associated to the given {@code Player}
     */
    @Override
    public User getUserByPlayer(Player player) {
        return colorUserMap.getOrDefault(player.getColor(), null);
    }

    /**
     * Goes frenzy.
     *
     * This flips all players' boards where applicable
     * and prepares the last turn of all players
     * according to the game's criteria.
     *
     * Stops the generation of new turns.
     */
    /* friendly */ void goFrenzy() {
        if(isFrenzy) throw new IllegalStateException();
        List<Turn> turnList = turnQueue.getList();
        boolean precedeFirstPlayer = false;

       for(Turn t : turnList) {
           Player activePlayer = t.getActivePlayer();
           precedeFirstPlayer = precedeFirstPlayer || activePlayer.hasFirstPlayerCard();
           activePlayer.goFrenzy(precedeFirstPlayer);
       }

       isFrenzy = true;
    }


    private void distributeScore(Player p) {

        // assign bonus point for double kill


        // score daage boards for
        for(Player q : this.players) {
            q.addScore(
                     p.getScoreForPlayer(q)
             );
        }
    }

    private void registerDeath(Player p) {
        Player killer = p.getDamager(DEATH_SHOT_INDEX);
        int amount = 1;
        amount += null == p.getDamager(OVERKILL_SHOT_INDEX) ? 0 : 1;
        killTrack.addKill(killer, amount);
    }

    public boolean isOver() {
        return isOver;
    }

    @Override
    public int getTurnTime() {
        return turnTime;
    }

    @Override
    public void setKillTrackListener(KillTrackListener listener) {
        if(!init) listenerRegistry.setKillTrackListener(listener);
        else killTrack.setListener(listener);
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public void declareOver() {
        isOver = true;
        // persistence?
    }
}
