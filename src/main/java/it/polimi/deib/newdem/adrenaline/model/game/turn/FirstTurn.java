package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FirstTurn extends TurnBaseImpl {

    /**
     * This class is currently a stub
     *
     * Logic for spawning in needs to be added
     *
     * What and how to override is not yet defined
     *
     */
    @Override
    protected void performInitialActions() throws TurnInterruptedException {
        spawn();
    }

    private void spawn() throws TurnInterruptedException {
        Player p = getActivePlayer();
        PlayerInventory inventory = p.getInventory();
        Map map = p.getGame().getMap();
        PowerUpCard selectedPup = null;

        // draw card
        // Adds automatically to p's inventory
        p.drawCard();
        p.drawCard();

        List<PowerUpCard> legalChoiches = inventory.getAllPowerUps();
        try{
            selectedPup = askPowerUpToHuman(legalChoiches);
            enterGameRoutine(p,map,selectedPup);
        }
        catch (InterruptExecutionException e) {
            Collections.shuffle(legalChoiches);
            selectedPup = legalChoiches.get(0);
            enterGameRoutine(p,map,selectedPup);
            throw new TurnInterruptedException();
        }
    }

    private void enterGameRoutine(Player p, Map map, PowerUpCard selectedPup) {
        p.getInventory().removePowerUp(selectedPup);
        Tile target = map.getSpawnPointFromColor(selectedPup.getEquivalentAmmo());
        map.movePlayer(getActivePlayer(), target);
    }

    public FirstTurn(Player activePlayer) {
        super(activePlayer);
    }

}
