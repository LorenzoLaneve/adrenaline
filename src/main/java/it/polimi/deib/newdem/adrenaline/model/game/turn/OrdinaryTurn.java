package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class OrdinaryTurn extends TurnBaseImpl {

    @Override
    protected void performInitialActions() {
        //TODO implement
        // requires action anc listeners
        // if dead, spawn
        // User u = getActivePlayer().getGame().getUserByPlayer(getActivePlayer());
        // u.sendDialog();
    }

    @Override
    protected void endOfTurn() {
        //TODO implement
        // requires action anc listeners
    }

    public OrdinaryTurn(Player activePlayer) {
        super(activePlayer);
        // TODO implement
    }

    public void turnWillStart() {
        // TODO implement
    }
}
