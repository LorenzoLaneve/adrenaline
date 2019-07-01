package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

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
    protected void performInitialActions() {
        //TODO implement
        Player p = getActivePlayer();
        Map map = p.getGame().getMap();
        // requires action anc listeners

        // draw card
        p.drawCard();
        p.drawCard();

        // select spawn card
        try {
            PowerUpCard selection = null;
            do {
                try {
                    selection = getDataSource().choosePowerUpCard(p.getInventory().getPowerUps());
                } catch (UndoException e) {
                    // do nothing
                }
            } while (null == selection);
            p.getInventory().removePowerUp(selection);

            // get set pos in map
            Tile target = map.getSpawnPointFromColor(selection.getEquivalentAmmo());
            map.movePlayer(getActivePlayer(), target);
        } catch (InterruptExecutionException x) {

            // spawn the player in random location
            AmmoColor randomColor = AmmoColor.values()[(int)(Math.random()*3)];

            Tile target = map.getSpawnPointFromColor(randomColor);
            map.movePlayer(getActivePlayer(), target);
            throw x;
        }

    }

    public FirstTurn(Player activePlayer) {
        super(activePlayer);
    }

}
