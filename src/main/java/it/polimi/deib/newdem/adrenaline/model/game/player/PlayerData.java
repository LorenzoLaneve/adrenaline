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

    public List<Integer> getPowerUpCards() {
        return new ArrayList<>(powerUpCards);
    }

    public List<Integer> getReadyWeaponCards() {
        return new ArrayList<>(readyWeaponCards);
    }

    public List<Integer> getUnloadedWeaponCards() {
        return new ArrayList<>(unloadedWeaponCards);
    }

    public Map<AmmoColor, Integer> getAmmos() {
        return new EnumMap<>(ammos);
    }

    public boolean isActionBoardFrenzy() {
        return isActionBoardFrenzy;
    }

    public boolean isDamageBoardFrenzy() {
        return isDamageBoardFrenzy;
    }

    public List<PlayerColor> getDamages() {
        return new ArrayList<>(damages);
    }

    public Map<PlayerColor, Integer> getMarks() {
        return new EnumMap<>(marks);
    }

    public PlayerColor getColor() {
        return color;
    }

    public TilePosition getPosition() {
        return position;
    }

    public int getDeaths() {
        return deaths;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean hasFirstPlayerCard() {
        return hasFirstPlayerCard;
    }

    public int getScore() {
        return score;
    }

}
