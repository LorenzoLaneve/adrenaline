package it.polimi.deib.newdem.adrenaline.model.game.rigged;

import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.NotSpawnPointTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class GameRigged extends GameImpl {

    public GameRigged(GameParameters parameters) {
        super(parameters);
    }

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

    public void setPowerupDeck(Deck<PowerUpCard> deck) {
        this.powerUpDeck = deck;
    }

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
