package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;

public class PlayerImpl implements Player {

    private PlayerInventory inventory;
    private ActionBoard actionBoard;
    private DamageBoard damageBoard;
    private PlayerColor color;
    private Game game;
    private String name;
    private int deaths;
    private boolean isDead; // maybe inferred?
    private boolean isInit;

    /*
    public PlayerImpl() {
        //TODO do what now?
    }
    */

    public PlayerImpl(PlayerColor color, Game game, String name) {
        this.color = color;
        this.game = game;
        this.name = name;
        this.deaths = 0;
        this.isDead = false;
        this.isInit = false;
        this.inventory = null;
        this.damageBoard = null;
    }

    /**
     * Initializes this player.
     *
     * This player's inventory, damage and action boards are created.
     */
    public void init() {
        this.damageBoard = new OrdinaryDamageBoard(this);
        this.actionBoard = new OrdinaryActionBoard();
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
    @Override
    public String getName() {
        return this.name;
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
     * @return
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
     * @return
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
        //TODO notify event
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
    }
}
