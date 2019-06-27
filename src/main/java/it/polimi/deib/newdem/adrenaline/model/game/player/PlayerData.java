package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PlayerData implements Serializable {
    private List<Integer> powerUpCards;
    private List<Integer> readyWeaponCards;
    private List<Integer> unloadedWeaponCards;
    private Map<AmmoColor, Integer> ammos;
    private boolean isActionBoardFrenzy;
    private boolean isDamageBoardFrenzy;
    private List<PlayerColor> damages;
    private Map<PlayerColor, Integer> marks;
    private PlayerColor color;
    private TilePosition position;
    private int deaths;
    private boolean isDead;
    private boolean hasFirstPlayerCard;
    private int score;

    PlayerData(Player player) {
        PlayerInventory inventory = player.getInventory();

        powerUpCards = new ArrayList<>();
        for(int i = 0; i < inventory.getPowerUps().size(); i++){
            powerUpCards.add(inventory.getPowerUps().get(i).getCardID());
        }

        readyWeaponCards = new ArrayList<>();
        for(int i = 0; i < inventory.getReadyWeapons().getWeapons().size(); i++){
            readyWeaponCards.add(inventory.getReadyWeapons().getWeapons().get(i).getCardID());
        }
        unloadedWeaponCards = new ArrayList<>();
        for(int i = 0; i < inventory.getUnloadedWeapons().getWeapons().size(); i++){
            unloadedWeaponCards.add(inventory.getUnloadedWeapons().getWeapons().get(i).getCardID());
        }
        ammos = new EnumMap<>(AmmoColor.class);
        ammos.put(AmmoColor.RED, inventory.getRed());
        ammos.put(AmmoColor.BLUE, inventory.getBlue());
        ammos.put(AmmoColor.YELLOW, inventory.getYellow());
        isActionBoardFrenzy = player.isActionBoardFrenzy();
        isDamageBoardFrenzy = player.getDamageBoard().isFrenzy();
        damages = new ArrayList<>();
        for(int i = 0; i < player.getDamageBoard().getTotalDamage(); i++){
            damages.add(player.getDamageBoard().getDamager(i).getColor());
        }
        marks = new EnumMap<>(PlayerColor.class);
        for(Map.Entry<Player, Integer> e : player.getDamageBoard().getMarksMap().entrySet()) {
            marks.put(e.getKey().getColor(), e.getValue());
        }
        color = player.getColor();
        if(null != player.getTile()) {
            position = player.getTile().getPosition();
        }
        else {
            position = new TilePosition(0,0);
        }
        deaths = player.getDeaths();
        isDead = player.isDead();
        hasFirstPlayerCard = player.hasFirstPlayerCard();
        score = player.getScore();
    }

    public List<Integer> getPowerUpCards() {
        return powerUpCards;
    }

    public List<Integer> getReadyWeaponCards() {
        return readyWeaponCards;
    }

    public List<Integer> getUnloadedWeaponCards() {
        return unloadedWeaponCards;
    }

    public Map<AmmoColor, Integer> getAmmos() {
        return ammos;
    }

    public boolean isActionBoardFrenzy() {
        return isActionBoardFrenzy;
    }

    public boolean isDamageBoardFrenzy() {
        return isDamageBoardFrenzy;
    }

    public List<PlayerColor> getDamages() {
        return damages;
    }

    public Map<PlayerColor, Integer> getMarks() {
        return marks;
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
