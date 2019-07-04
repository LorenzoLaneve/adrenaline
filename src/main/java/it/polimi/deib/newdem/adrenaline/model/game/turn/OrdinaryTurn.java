package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

import java.util.Collections;
import java.util.List;

public class OrdinaryTurn extends TurnBaseImpl {

    public OrdinaryTurn(Player activePlayer) {
        super(activePlayer);
        // Or is it?
    }

    @Override
    protected void performInitialActions() throws TurnInterruptedException {
        // if dead, spawn
        if(getActivePlayer().isDead()) {
            respawn();
        }
    }

    private void respawn() throws TurnInterruptedException {
        Player p = getActivePlayer();
        Map map = p.getGame().getMap();
        Deck<PowerUpCard> powerUpDeck = p.getGame().getPowerUpDeck();

        List<PowerUpCard> availableChoiches = p.getInventory().getAllPowerUps();
        PowerUpCard newCard = null;

        try {
            newCard = powerUpDeck.draw();
            availableChoiches.add(newCard);
        }
        catch (NoDrawableCardException e) {
            // this should never happen
            // there should always be at least one
            // drawable powerup
            throw new IllegalStateException();
        }

        PowerUpCard chosenCard = null;

        try {
            chosenCard = askPowerUpToHuman(availableChoiches);
            enterGameRoutine(p, map, powerUpDeck, chosenCard, newCard);
        }
        catch (InterruptExecutionException e) {
            // timeout, CPU chooses at random
            Thread.currentThread().interrupt();
            Collections.shuffle(availableChoiches);
            chosenCard = availableChoiches.get(0);
            enterGameRoutine(p, map, powerUpDeck, chosenCard, newCard);
            throw new TurnInterruptedException();
        }
    }

    private void enterGameRoutine(Player p,
                                  Map map,
                                  Deck<PowerUpCard> powerUpDeck,
                                  PowerUpCard chosenCard,
                                  PowerUpCard newCard) {
        p.reportDeath(false);
        map.movePlayer(p, map.getSpawnPointFromColor(chosenCard.getEquivalentAmmo()));

        if(p.getInventory().getAllPowerUps().contains(chosenCard)) {
            try {
                p.getInventory().removePowerUp(chosenCard);
                powerUpDeck.discard(chosenCard);
                p.getInventory().addPowerUp(newCard);
            }
            catch (OutOfSlotsException e) {
                throw new IllegalStateException(e);
            }
        }
        else {
            assert chosenCard == newCard;
            powerUpDeck.discard(newCard);
        }
    }
}
