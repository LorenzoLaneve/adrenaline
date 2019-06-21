package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
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
        PowerUpCard selection = getDataSource().chooseCard(p.getInventory().getPowerUps());

        // get set pos in map
        Tile target = map.getSpawnPointFromColor(selection.getEquivalentAmmo());
        map.movePlayer(getActivePlayer(), target);

    }

    public FirstTurn(Player activePlayer) {
        super(activePlayer);
        //TODO implement
        // requires action anc listeners
    }

    public void start() {
        //TODO implement
        // requires action anc listeners
    }

    public void turnWillStart() {
        //TODO implement
        // requires action anc listeners
    }


}
