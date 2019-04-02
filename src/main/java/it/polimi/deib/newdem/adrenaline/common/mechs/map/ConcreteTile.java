package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.WeaponSet;

import java.util.List;

public abstract class ConcreteTile implements Tile {
    // TODO figure out data structure

    private Room room;

    private List<Player> players;

    @Override
    public Map getMap() {
        // TODO implement
        return null;
    }

    @Override
    public Room getRoom() {
        // TODO implement
        return null;
    }

    @Override
    public TilePosition getPosition() {
        // TODO implement
        return null;
    }

    @Override
    public int distanceFrom(Tile t) {
        // TODO implement
        return 0;
    }

    @Override
    public List<Player> getPlayers() {
        // TODO implement
        return null;
    }

    @Override
    public List<Tile> getAdjacentTiles() {
        // TODO implement
        return null;
    }
}
