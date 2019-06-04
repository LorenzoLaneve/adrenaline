package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.NoDrawableCardException;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class DropPickupGameChange implements GameChange {

    private Player player;

    private Tile dropTile;

    public DropPickupGameChange(Player player, Tile dropTile) {
        this.player = player;
        this.dropTile = dropTile;
    }

    @Override
    public void update(Game game) {

        DropInstance dropInstance = null;

        try{
            dropInstance = dropTile.grabDrop();
        }catch (Exception e){
            //TODO
        }

        if(dropInstance.hasPowerUp()){
            PowerUpCard card;
            try{
                card = game.getPowerUpDeck().draw();
                try{
                    player.getInventory().addPowerUp(card);
                }catch (OutOfSlotsException e){
                    //TODO
                }
            }catch (NoDrawableCardException e){
                throw new IllegalStateException();
            }
        }


    }

    @Override
    public void revert(Game game) {
        // TODO
    }

}
