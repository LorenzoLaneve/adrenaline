package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.Config;
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
import it.polimi.deib.newdem.adrenaline.model.game.utils.FileUtils;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.*;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<WeaponCard> weaponDeck;
    private Deck<DropInstance> dropDeck;
    private GameListener listener;
    private java.util.Map<PlayerColor, User> colorUserMap;
    private boolean init;
    private ListenerRegistry listenerRegistry;
    private int minPlayers;
    private int maxPlayers;

    public static final int MAX_PLAYERS_PER_GAME = 5;
    public static final String WEAPON_DECK_PATH = "cards/basedeck.json";
    public static final String POWERUP_DECK_PATH = "cards/powerupdeck.json";
    public static final String DROP_DECK_PATH = "cards/droptiledeck.json";

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
        minPlayers = parameters.getMinPlayers();
        maxPlayers = parameters.getMaxPlayers();
    }

    private GameImpl(){}

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
        if(!players.isEmpty()) throw new IllegalStateException();
        boolean isFirst = true;

        for(java.util.Map.Entry<PlayerColor,User> e : colorUserMap.entrySet()) {
            Player newPlayer = new PlayerImpl(
                    e.getKey(),
                    this
            );

            if(isFirst){
                newPlayer.assignFirstPlayerCard();
                isFirst = false;
            }
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
        for (Player p : players) {
            turnQueue.enqueue(new FirstTurn(p));
        }
    }

    private void initPlayers() {
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
        // TODO bind to gp min
        if(colorUserMap.entrySet().size() < minPlayers) throw new IllegalStateException();

        bindElementsListeners();

        // load cards
        importWeaponDeck(WEAPON_DECK_PATH);

        //load decks
        importPowerUpDeck(POWERUP_DECK_PATH);
        importDropDeck(DROP_DECK_PATH);

        // create Players
        createNewPlayers();

        // register players on listener
        notifyPlayersIngress();

        // prepare round robin
        setUpRoundRobin();

        // init players
        initPlayers();

        // fill tiles
        //TODO load non-empty decks from json
        refillTiles(); // no decks, breaks tests for now

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

    private void importWeaponDeck(String filePath) {
        String decodedPath = FileUtils.getAbsoluteDecodedFilePath(filePath, this.getClass());
        WeaponDeck factory;
        try {
            factory = WeaponDeck.fromJson(decodedPath);
        }
        catch(InvalidJSONException e) {
            throw new IllegalStateException();
        }
        weaponDeck = factory.createNewDeck();
    }

    private void importPowerUpDeck(String filePath) {
        String decodedPath = FileUtils.getAbsoluteDecodedFilePath(filePath, this.getClass());
        PowerUpDeck factory;
        try {
            factory = PowerUpDeck.fromJson(decodedPath);
        }
        catch(InvalidJSONException e) {
            throw new IllegalStateException();
        }
        powerUpDeck = factory.createNewDeck();
    }

    private void importDropDeck(String filePath) {
        String decodedPath = FileUtils.getAbsoluteDecodedFilePath(filePath, this.getClass());
        DropDeck factory;
        try {
            factory = DropDeck.fromJson(decodedPath);
        }
        catch(InvalidJSONException e) {
            throw new IllegalStateException();
        }
        dropDeck = factory.createNewDeck();
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

        // refill tiles
        // both drops
        // and weapons
        // TODO load decks from json
        refillTiles(); // needs non-empty drop deck

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
        // score daage boards for dead player p
        for(Player q : this.players) {
            q.addScore(
                     p.getScoreForPlayer(q)
             );
        }
    }

    private void registerDeath(Player p) {
        p.addSkull();
        Player killer = p.getDamager(DEATH_SHOT_INDEX);
        int killtrackMarkAmount = 1;
        killtrackMarkAmount += null == p.getDamager(OVERKILL_SHOT_INDEX) ? 0 : 1;
        killTrack.addKill(killer, killtrackMarkAmount);
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

    public Deck<PowerUpCard> getPowerUpDeck() {
        return powerUpDeck;
    }

    private void refillTiles(){
        // TODO
        for(Tile t : map.getAllTiles()){
            if(t.hasSpawnPoint()) {
                refillSpawn(t);
            }
            else {
                // t has NOT spawnpoint
                // ==> t is ordinaryTile
                refillDrop(t);
            }
        }
    }

    private void refillSpawn(Tile t) {
        while (t.inspectWeaponSet().getWeapons().size() < 3) {
            try {
                t.addWeapon(weaponDeck.draw());
            }
            catch (NoDrawableCardException e) {
                // this CAN happen, and that's ok.
                // We just ignore it and move on,
                // as designed in game.
            }
            catch (OutOfSlotsException |
                    WeaponAlreadyPresentException |
                    NotSpawnPointTileException e) {
                // this should NOT happen, so we report it
                throw new IllegalStateException(e);
            }
        }

    }

    private void refillDrop(Tile t) {
        if(t.missingDrop()){
            try {
                t.addDrop(dropDeck.draw());
            }
            catch (NoDrawableCardException e) {
                // this CAN happen, and that's ok.
                // We just ignore it and move on,
                // as designed in game.
            }
            catch (DropAlreadyPresentException |
                    NotOrdinaryTileException e) {
                // this should NOT happen, so we report it
                throw new IllegalStateException(e);
            }
        }
    }
}
