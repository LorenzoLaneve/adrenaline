package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.DamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.FrenzyDamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.OrdinaryDamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.*;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;

public class PlayerImpl implements Player {

    private PlayerListener listener;
    private PlayerInventory inventory;
    private ActionBoard actionBoard;
    private DamageBoard damageBoard;
    private PlayerColor color;
    private Game game;
    private Tile position;
    private int deaths;
    private boolean isDead; // maybe inferred?
    private boolean isInit;
    private boolean hasFirstPlayerCard;
    private int score;

    public static Player makePlayer(PlayerColor color, Game game) {
        Player p = new PlayerImpl(color,game, "ILLEGAL_NAME");
        p.init();
        return p;
    }

    public PlayerImpl(PlayerColor color, Game game, String name) {
        this.color = color;
        this.game = game;
        this.listener = new NullPlayerListener();
        this.position = null;
        this.deaths = 0;
        this.isDead = false;
        this.isInit = false;
        this.inventory = null;
        this.damageBoard = null;
        this.score = 0;
    }

    /**
     * Initializes this player.
     *
     * This player's new inventory, damage and action boards are created.
     */
    public void init() {
        this.damageBoard = new OrdinaryDamageBoard(this);
        this.actionBoard = new ActionBoardImpl();
        this.inventory = new PlayerInventory(this);
        this.isInit = true;
    }

    /**
     * Retuns the game this player belongs to
     *
     * @return the game
     */
    @Override
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns this player's {@code PlayerInventory}
     *
     * @return the inventory
     */
    @Override
    public PlayerInventory getInventory() {
        if(!isInit) throw new PlayerNotInitializedException();
        return this.inventory;
    }

    /**
     * Retuns this player's name
     *
     * @return the name
     */
    //TODO reflect on user
    @Override
    public String getName() {
        return game.getUserByPlayer(this).getName();
    }


    @Override
    public Tile getTile() {
        return position;
    }

    @Override
    public void setTile(Tile dest) {
        this.position = dest;
    }

    /**
     * Returns this player's color
     *
     * @return the color
     */
    @Override
    public PlayerColor getColor() {
        return this.color;
    }

    /**
     * Returns all the legal moves this player can take
     *
     * @return all legal moves
     */
    @Override
    public List<ActionFactory> getMoves() {
        if(!isInit) throw new PlayerNotInitializedException();
        List<ActionFactory> factories = new ArrayList<>();
        factories.addAll(this.actionBoard.getBasicActions());
        factories.addAll(damageBoard.getAdditionalActions());
        if(!this.inventory.getPowerUps().isEmpty()){
            factories.add(new ConcreteActionFactory(USE_POWERUP));
        }
        return factories;
    }

    /**
     * Returns the total amount of deaths for this player.
     *
     * @return death count
     */
    @Override
    public int getDeaths() {
        if(!isInit) throw new PlayerNotInitializedException();
        return this.deaths;
    }

    /**
     * Returns the amount of damage currently assigned to this player
     *
     * @return damage
     */
    @Override
    public int getTotalDamage() {
        if(!isInit) throw new PlayerNotInitializedException();
        return damageBoard.getTotalDamage();
    }

    /**
     * Identifies the player that dealt the {@code cell}-th damage to this player.
     *
     * @param cell the index of the cell. Between 0 and 11 inclusive.
     * @return player who dealt the damage. Can be null.
     */
    @Override
    public Player getDamager(int cell) {
        if(!isInit) throw new PlayerNotInitializedException();
        return damageBoard.getDamager(cell);
    }

    /**
     * Calculates the total amount of damage dealt to this player by an opposing {@code Player}
     *
     * @param player the opponent from which the damage is calculated
     * @return damage dealt
     */
    @Override
    public int getDamageFromPlayer(Player player) {
        if(!isInit) throw new PlayerNotInitializedException();
        return damageBoard.getTotalDamageFromPlayer(player);
    }

    /**
     * Calculates the total amount of marks received from an opposing {@code Player}.
     *
     * @param player an opponent
     * @return marks taken. Between zero and three inclusive.
     */
    @Override
    public int getMarksFromPlayer(Player player) {
        if(!isInit) throw new PlayerNotInitializedException();
        return damageBoard.getTotalMarksFromPlayer(player);
    }

    /**
     * Identifies wether or not this player is currently alive.
     *
     * @return is the player dead
     */
    @Override
    public boolean isDead() {
        if(!isInit) throw new PlayerNotInitializedException();
        return isDead;
    }

    /**
     * Bind a {@code DamageBoard} to this player
     *
     * @param damageBoard the board to register.
     */
    @Override
    public void registerDamageBoard(DamageBoard damageBoard) {
        this.damageBoard = damageBoard;
    }

    /**
     * Receives damage from another player.
     *
     * @param dmgAmount amount, not negative
     * @param attacker attacker, not null, not this player
     */
    @Override
    public void takeDamage(int dmgAmount, Player attacker) {
        if(!isInit) throw new PlayerNotInitializedException();
        if(dmgAmount < 0 || null == attacker || this == attacker) throw new IllegalArgumentException();
        this.damageBoard.takeDamage(dmgAmount, attacker);

        isDead = damageBoard.getTotalDamage() >= DEATH_SHOT_INDEX;
        if (isDead) {
            Map map = getTile().getMap();
            if (map.getListener() != null) {
                map.getListener().playerDidDie(this);
            }
        }
    }

    /**
     * Receives marks from another player.
     *
     * @param markAmount amount, not negative
     * @param attacker attacker, not this player, not null
     */
    @Override
    public void takeMark(int markAmount, Player attacker) {
        if(!isInit) throw new PlayerNotInitializedException();
        if(markAmount < 0 || null == attacker || this == attacker) throw new IllegalArgumentException();
        this.damageBoard.takeMark(markAmount, attacker);
        //TODO notify event
        listener.playerTookMark(markAmount, attacker);
    }

    public void assignFirstPlayerCard() {
        hasFirstPlayerCard = true;
    }

    @Override
    public boolean hasFirstPlayerCard() {
        return hasFirstPlayerCard;
    }

    /**
     * Flips the action board and damage board where applicable.
     *
     * @param precedesFirstPlayer does the player precedes the first player in this last turn?
     */
    @Override
    public void goFrenzy(boolean precedesFirstPlayer) {

        actionBoard.goFrenzy(precedesFirstPlayer);

        if(0 == damageBoard.getTotalDamage()) {
            registerDamageBoard(
                    new FrenzyDamageBoard(this, this.damageBoard.getMarksMap())
            );
        }
    }

    /**
     * Calculates the score for the given {@code Player} gained from the damage to
     * this {@code Player}.
     *
     * @param player whose score will be calculated
     * @return score, always an integer; zero if no score should be assigned.
     */
    @Override
    public int getScoreForPlayer(Player player) {
        if(!isInit) throw new PlayerNotInitializedException();
        if(null == damageBoard) throw new IllegalStateException();
        return damageBoard.getScoreForPlayer(player);
    }

    @Override
    public void addScore(int points) {
        score += points;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setListener(PlayerListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean isConnected() {
        return game.getUserByPlayer(this).isConnected();
    }

    @Override
    public int getMovesAmount() {
        return actionBoard.getIterations();
    }
}
