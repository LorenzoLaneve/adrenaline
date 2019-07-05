package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.ArrayList;
import java.util.List;

public class MockInventory extends PlayerInventory {

    /**
     * Simple Mock Object Used for testing purposes.
     */

    private List<PowerUpCard> cards;

    public MockInventory(Player player) {
        super(player);
        cards = new ArrayList<>();
    }

    public void addPowerUp(PowerUpCard card){
        this.cards.add(card);
    }

    public List<PowerUpCard> getPowerUpCards() {
        return new ArrayList<>(this.cards);
    }
}
