package it.polimi.deib.newdem.adrenaline.model.game.rigged;

import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.NotSpawnPointTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Riggable game for testing purposes
 */
public class GameRigged extends GameImpl {

    /**
     * Creates a new game as if it were not rigged
     * @param parameters according to which create a game
     */
    public GameRigged(GameParameters parameters) {
        super(parameters);
    }

    /**
     * Replace the game's weapon deck with the one provided.
     * @param deck to replace the game's with
     */
    public void setWeaponDeck(Deck<WeaponCard> deck) {
        for(AmmoColor color : AmmoColor.values()) {
            try {
                Tile spawn = getMap().getSpawnPointFromColor(color);
                spawn.grabWeapon(spawn.inspectWeaponSet().getWeapons().get(0));
                spawn.grabWeapon(spawn.inspectWeaponSet().getWeapons().get(0));
                spawn.grabWeapon(spawn.inspectWeaponSet().getWeapons().get(0));
            }
            catch (NotSpawnPointTileException e) {
                throw new IllegalStateException();
            }
        }
        this.weaponDeck = deck;
        refillTiles();
    }

    /**
     * Replace this game's powerup deck with the one provided
     * @param deck to replace the game's with
     */
    public void setPowerupDeck(Deck<PowerUpCard> deck) {
        this.powerUpDeck = deck;
    }

    /**
     * Replace this game's drop deck with the one provided.
     *
     * Clear all tiles from drops belonging to the previous deck
     * and refill them with drops from this deck
     *
     * @param deck to replace the game's with
     */
    public void setDropDeck(Deck<DropInstance> deck) {
        for(Tile tile : getMap().getAllTiles()) {
            try{
                tile.grabDrop();
            }
            catch (NotOrdinaryTileException e) {
                // ok
            }
        }

        this.dropDeck = deck;
        refillTiles();
    }
}
