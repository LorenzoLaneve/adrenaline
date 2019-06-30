package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class OrdinaryTurn extends TurnBaseImpl {

    @Override
    protected void performInitialActions() {
        //TODO implement
        // requires action anc listeners
        // if dead, spawn
        if(getActivePlayer().isDead()) {
            respawn();
        }
    }

    public OrdinaryTurn(Player activePlayer) {
        super(activePlayer);
        // TODO implement
        // Or is it?
    }

    public void turnWillStart() {
        // TODO implement
    }

    private void respawn() {
        Player p = getActivePlayer();
        Map m = p.getGame().getMap();

        p.drawCard();
        PowerUpCard choice = null;
        do {
            try{
                choice = getDataSource().chooseCard(p.getInventory().getPowerUps());
            }
            catch (UndoException e) {
                // do nothing
            }
        }
        while (null == choice);

        m.movePlayer(p, m.getSpawnPointFromColor(choice.getEquivalentAmmo()));

    }
}
