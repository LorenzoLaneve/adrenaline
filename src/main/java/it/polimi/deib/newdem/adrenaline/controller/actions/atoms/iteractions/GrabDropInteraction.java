package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.NoDrawableCardException;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class GrabDropInteraction extends InteractionBase {

    private DropInstance dropInstance;
    private PowerUpCard pupCard;

    public GrabDropInteraction(InteractionContext context) {
        super(context);
        dropInstance = null;
        pupCard = null;
    }

    @Override
    public void execute() {
        Tile dropTIle = context.getActor().getTile();
        Player player = context.getActor();

        try {
             dropInstance = dropTIle.grabDrop();
             if(null == dropInstance)  { return; }

             player.getInventory().addAmmoSet(dropInstance.getAmmos());
             if(player.getInventory().canAcceptPowerUp() &&
                dropInstance.hasPowerUp()) {
                 attemptDraw();
             }

             context.getGame().getDropDeck().discard(dropInstance);
        }
        catch (NotOrdinaryTileException e) {
            throw new IllegalStateException(e);
        }
    }

    private void attemptDraw() {
        try {
            pupCard = context.getActor().getGame().getPowerUpDeck().draw();
            context.getActor().getInventory().addPowerUp(pupCard);
        }
        catch (NoDrawableCardException x) {
            // too bad, you're out of pup.
            pupCard = null;
        }
        catch (OutOfSlotsException y) {
            throw new IllegalStateException(y);
        }
    }

    @Override
    public void revert() {
        // don't care, this will never be called as this always concludes an Action.
    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
