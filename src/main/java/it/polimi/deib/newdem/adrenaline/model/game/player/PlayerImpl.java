package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.*;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;

/**
 * An implementation of {@code Player}
 */
public class PlayerImpl implements Player {

    private PlayerListener listener;
    private PlayerInventory inventory;
    private ActionBoard actionBoard;
    private DamageBoard damageBoard;
    private PlayerColor color;
    private Game game;
    private Tile position;
    private int deaths;
    private boolean isDead;
    private boolean isInit;
    private boolean hasFirstPlayerCard;
    private boolean diedThisTurn;
    private int score;

    /**
     * Creates a new player for the given game.
     *
     * The created player will not be initialized
     *
     * @param color this player's color
     * @param game this player will play in
    */
    public PlayerImpl(PlayerColor color, Game game) {
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
     * Creates and initializes a new player for the given game
     *
     * @param color of the layer to create
     * @param game to which the new player will belong
     * @return new player
     */
    public static Player makePlayer(PlayerColor color, Game game) {
        Player p = new PlayerImpl(color,game);
        p.init();
        return p;
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

    @Override
    public PlayerListener getListener() {
        return listener;
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
     * Returns this player's name
     *
     * @return the name
     */
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

        // if no callable powerups are in this player's inventory,
        // do not ak for them.
        if(!this.inventory.getPowerUpByTrigger(PowerUpTrigger.CALL).isEmpty()){
            factories.add(new ConcreteActionFactory(USE_POWERUP));
        }

        // if no weapon is loaded, remove any shoot action
        ActionType shootType = new ActionType(AtomicActionType.SHOOT);
        if(inventory.getLoadedWeapons().isEmpty()) {
            factories.removeIf(e -> shootType.covers(e.getType()));
        }

        // if this player is not elegible for it, remove reload action
        ActionType reloadType = new ActionType(RELOAD);
        if(!canReload()) {
            factories.removeIf(e -> reloadType.covers(e.getType()));
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
     * Note that if a previous damage board was set, its listener will be passed to the new damage board.
     *
     * @param damageBoard the board to register.
     */
    @Override
    public void registerDamageBoard(DamageBoard damageBoard) {
        DamageBoardListener damageBoardListener = this.damageBoard.getListener();

        if (damageBoardListener != null) {
            damageBoardListener.boardDidClear();
            this.damageBoard.setListener(null);
            damageBoard.setListener(damageBoardListener);

            if (damageBoard.isFrenzy()) {
                damageBoardListener.boardDidSwitchToFrenzy();
            }
        }

        this.damageBoard = damageBoard;
    }

    @Override
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
        if (listener != null) {
            listener.playerDidUpdateScore(this, score);
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setListener(PlayerListener listener) {
        this.listener = listener;
        if (listener != null) {
            listener.playerDidInit(generatePlayerData());
        }
    }

    @Override
    public boolean isConnected() {
        return game.getUserByPlayer(this).isConnected();
    }

    @Override
    public int getMovesAmount() {
        return actionBoard.getIterations();
    }

    @Override
    public void drawCard() {
        try {
            PowerUpCard card = game.getPowerUpDeck().draw();
            getInventory().addPowerUp(card);
        }
        catch (NoDrawableCardException | OutOfSlotsException e) {
            // this should never happen
            throw new IllegalStateException(e);
        }
    }

    @Override
    public DamageBoard getDamageBoard() {
        return damageBoard;
    }

    @Override
    public ActionBoard getActionBoard() {
        return actionBoard;
    }

    @Override
    public void reportDeath(boolean isDead) {
        if (isDead) {
            this.isDead = true;
            this.diedThisTurn = true;
            Map map = getGame().getMap();
            if (map.getListener() != null) {
                map.getListener().playerDidDie(this);
            }
        }
        else {
            this.isDead = false;
            this.diedThisTurn = false;
        }
    }

    @Override
    public void addSkull() {
        deaths++;
    }

    @Override
    public boolean isActionBoardFrenzy() {
        return actionBoard.isFrenzy();
    }

    @Override
    public PlayerData generatePlayerData() {
        return new PlayerData(this);
    }

    @Override
    public boolean canReload() {
        for(Weapon w : inventory.getDischargedWeapons()) {
            if(inventory.canPay(w.getCard().getReloadPrice())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean diedThisTurn() {
        return diedThisTurn;
    }

    @Override
    public void resetTurnDeath() {
        diedThisTurn = false;
    }
}
