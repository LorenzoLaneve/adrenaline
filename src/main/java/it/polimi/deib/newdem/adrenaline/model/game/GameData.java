package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrack;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.RoundRobin;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

/**
 * Holds all gameplay information needed to build a new instance of game
 * at any state of gameplay
 */
public class GameData {

    private Map map;
    private RoundRobin<Turn> turnQueue;
    private KillTrack killTrack;
    // private List<Player> players;
    private boolean isFrenzy; // BAD, enum or states
    private boolean isOver;
    private int turnTime;
    private Deck<WeaponCard> weaponDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<DropInstance> dropDeck;
    private java.util.Map<PlayerColor, User> colorUserMap;

    /**
     * Builds a new GameData for a newly created game
     * @return
     */
    static GameData forNewGame(){
        return null;
    }

    /**
     * Returns an instance of map,
     * with player positions,
     * drops,
     * weapons in spawnpoints
     * etc.
     *
     * @return
     */
    Map getMap() {
        return map;
    }

}
