package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;

public class GameImpl implements Game {

    private Map map;

    private RoundRobin<Turn> turnQueue;

    private final int killtrackStartSize;

    private KillTrack killTrack;

    private List<Player> players;

    private boolean isFrenzy;

    private GameListener listener;

    private java.util.Map<PlayerColor, User> colorUserMap;

    public static final int MAX_PLAYERS_PER_GAME = 5;

    /**
     * Builds a new game from the given {@code GameParameters}
     *
     * @param parameters to build the new game from
     */
    public GameImpl(GameParameters parameters) {
        map = parameters.getGameMap();
        killtrackStartSize = parameters.getKillTrackInitialLength();
        colorUserMap = parameters.getColorUserMap();
        players = new ArrayList<>(MAX_PLAYERS_PER_GAME);
        // ^ no players added
        turnQueue = new RoundRobin<>();
    }

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
    public void setListener(GameListener listener) {
        this.listener = listener;
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

    /**
     * Prepares this game for its first execution
     */
    public void init() {

        // create Players
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
            players.add(newPlayer);
        }

        // calling reset
        reset();
    }


    /**
     * Prepares this game for a new game.
     *
     * resets kill track
     * builds initial turns
     * sets the game to not be in frenzy (therefore ordinary gameplay state)
     */
    public void reset() {
        killTrack = new KillTrackImpl(killtrackStartSize);
        if(players.isEmpty()) {
            throw new IllegalStateException();
        }
        for (Player p : players) {
            p.init();
            //TODO notify game view
            turnQueue.enqueue(new FirstTurn(p));
        }
        isFrenzy = false;
    }

    /**
     * Returns the next turn.
     *
     * @return
     */
    @Override
    public Turn getNextTurn() {
        return turnQueue.next();
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
        // register kills
        for(Player p : players) {
            if(p.isDead()) {
                killTrack.registerKill(p.getDamager(DEATH_SHOT_INDEX));
                //TODO
                // remove player from map
                // setup respawn (if not already done)
            }
        }
        //  assign score
        //  update killtrack

        turnQueue.enqueue(new OrdinaryTurn(turn.getActivePlayer()));
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
     * This fips all players' boards where applicable
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
}
