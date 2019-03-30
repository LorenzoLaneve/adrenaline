package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

import java.util.List;

public class ConcreteRoom implements Room {
    // TODO figure out data structure

    // discussed on Discord
    // inferred from getMap()
    private Map map;

    private List<Tile> tiles;

    public ConcreteRoom(Map map, List<Tile> tiles) {
        // TODO review
        this.map = map;
        this.tiles = tiles;
    }

    @Override
    public List<Tile> getTiles() {
        // TODO implement
        return null;
    }

    @Override
    public Map getMap() {
        // TODO implement
        return null;
    }

    @Override
    public Player getPlayers() {
        // TODO implement
        return null;
    }

}
