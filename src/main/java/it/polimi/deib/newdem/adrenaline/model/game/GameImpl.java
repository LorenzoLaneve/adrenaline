package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.EnumMap;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;

public class GameImpl implements Game {

    private Map map;

    private RoundRobin<Turn> turnQueue;

    private final int killtrackStartSize;

    private KillTrack killTrack;

    private List<Player> players;

    private boolean isFrenzy;

    private List<GameListener> listeners;

    private EnumMap<PlayerColor, Player> playerColorMap;

    /**
     * Builds a new game from the given {@code GameParameters}
     *
     * @param parameters to build the new game from
     */
    public GameImpl(GameParameters parameters) {
        map = parameters.getMap();
        killtrackStartSize = parameters.getKillTrackInitialLength();
        playerColorMap = (EnumMap<PlayerColor, Player>) parameters.getColorPlayerMap();
        players = parameters.getPlayerOrder();
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

    /**
     * Identifies a {@code Player} in this game from its {@code PlayerColor}
     *
     * @param color of the desired {@code Player}
     * @return player of the given color in this game.
     * Returns null if no player is present for the given color.
     */
    @Override
    public Player getPlayerFromColor(PlayerColor color) {
        return playerColorMap.getOrDefault(color, null);
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
     * Prepares this game for a new game.
     *
     */
    public void reset() {
        killTrack = new KillTrackImpl(killtrackStartSize);
        for(Player p : players) {
            p.init();
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

    @Override
    public void setUserForColor(User user, PlayerColor color) {
        // TODO
    }

    @Override
    public void getUserByPlayer(Player player) {
        // TODO
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
