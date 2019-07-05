package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.Serializable;
import java.util.*;

/**
 * Data object representing a snapshot of the state of a player.
 * @see Player for further information.
 */
public class PlayerData implements Serializable {

    private ArrayList<Integer> powerUpCards;
    private ArrayList<Integer> readyWeaponCards;
    private ArrayList<Integer> unloadedWeaponCards;

    private EnumMap<AmmoColor, Integer> ammos;

    private boolean isActionBoardFrenzy;
    private boolean isDamageBoardFrenzy;

    private ArrayList<PlayerColor> damages;

    private EnumMap<PlayerColor, Integer> marks;

    private PlayerColor color;

    private TilePosition position;

    private int deaths;
    private boolean isDead;
    private boolean hasFirstPlayerCard;
    private int score;

    /**
     * Initializes the player data object with information about the given player.
     * @param player to take a snapshot of
     */
    PlayerData(Player player) {
        this.color = player.getColor();

        PlayerInventory inventory = player.getInventory();

        this.powerUpCards = new ArrayList<>();
        for(PowerUpCard powerUpCard : inventory.getAllPowerUps()){
            this.powerUpCards.add(powerUpCard.getCardID());
        }

        this.readyWeaponCards = new ArrayList<>();
        for(WeaponCard weapon : inventory.getReadyWeapons().getWeapons()){
            this.readyWeaponCards.add(weapon.getCardID());
        }

        this.unloadedWeaponCards = new ArrayList<>();
        for(WeaponCard weapon : inventory.getUnloadedWeapons().getWeapons()){
            this.unloadedWeaponCards.add(weapon.getCardID());
        }

        this.ammos = new EnumMap<>(AmmoColor.class);
        this.ammos.put(AmmoColor.RED, inventory.getRed());
        this.ammos.put(AmmoColor.BLUE, inventory.getBlue());
        this.ammos.put(AmmoColor.YELLOW, inventory.getYellow());

        this.isActionBoardFrenzy = player.isActionBoardFrenzy();
        this.isDamageBoardFrenzy = player.getDamageBoard().isFrenzy();

        this.damages = new ArrayList<>();
        for(int i = 0; i < player.getDamageBoard().getTotalDamage(); i++){
            this.damages.add(player.getDamageBoard().getDamager(i).getColor());
        }

        this.marks = new EnumMap<>(PlayerColor.class);
        for(Map.Entry<Player, Integer> e : player.getDamageBoard().getMarksMap().entrySet()) {
            this.marks.put(e.getKey().getColor(), e.getValue());
        }

        if(null != player.getTile()) {
            this.position = player.getTile().getPosition();
        } else {
            this.position = null;
        }

        this.deaths = player.getDeaths();
        this.isDead = player.isDead();
        this.hasFirstPlayerCard = player.hasFirstPlayerCard();
        this.score = player.getScore();
    }

    /**
     * Retrieves this player's powerup cards
     * @return powerup cards in this player's inventory
     */
    public List<Integer> getPowerUpCards() {
        return new ArrayList<>(powerUpCards);
    }

    /**
     * Retrieves the loaded weapon cards in this player's inventory
     * @return loaded weapon cards
     */
    public List<Integer> getReadyWeaponCards() {
        return new ArrayList<>(readyWeaponCards);
    }

    /**
     * Retrieves the discharged weapon cards in this player's inventory
     * @return discharged weapon cards
     */
    public List<Integer> getUnloadedWeaponCards() {
        return new ArrayList<>(unloadedWeaponCards);
    }

    /**
     * Retrieves the ammunitions in this player's inventory
     * @return map of ammunitions
     */
    public Map<AmmoColor, Integer> getAmmos() {
        return new EnumMap<>(ammos);
    }

    /**
     * Checks if this player's action board is in any frenzy state
     * @return is action board frenzy
     */
    public boolean isActionBoardFrenzy() {
        return isActionBoardFrenzy;
    }

    /**
     * Checks if this player's damage board is in any frenzy state
     * @return is damage board frenzy
     */
    public boolean isDamageBoardFrenzy() {
        return isDamageBoardFrenzy;
    }

    /**
     * Retrieves the damages on this player's action board as a list of {@code PlayerColors}
     *
     * @return damages currently on this player
     */
    public List<PlayerColor> getDamages() {
        return new ArrayList<>(damages);
    }

    /**
     * Retrieves the marks in this player's damage board
     * @return marks on this player's damage board
     */
    public Map<PlayerColor, Integer> getMarks() {
        return new EnumMap<>(marks);
    }

    /**
     * Retrieves this player's color
     *
     * @return this player's color
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Retrieves this player's position on its map
     *
     * @return current position
     */
    public TilePosition getPosition() {
        return position;
    }

    /**
     * Retrieves the amount of deaths of this player
     *
     * @return number of deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Checks if this player is currently dead
     *
     * @return is this player dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Checks if this player has the first player card
     *
     * @return has first player card
     */
    public boolean hasFirstPlayerCard() {
        return hasFirstPlayerCard;
    }

    /**
     * Retrieves this player's current score
     *
     * @return this player's score
     */
    public int getScore() {
        return score;
    }

}
