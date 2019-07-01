package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponSet;

public interface Tile {

    Map getMap();

    Room getRoom();

    TilePosition getPosition();

    int distanceFrom(Tile t);

    List<Player> getPlayers();

    List<Tile> getAdjacentTiles();

    boolean hasSpawnPoint();

    /**
     * @return DropInstance in the OrdinaryTile without removing it.
     */
    DropInstance inspectDrop();

    /**
     * @return weaponSet in the SpawnPointTile without removing it.
     */
    WeaponSet inspectWeaponSet();

    void addDrop(DropInstance drop) throws DropAlreadyPresentException, NotOrdinaryTileException;

    void addWeapon(WeaponCard weapon) throws NotSpawnPointTileException, OutOfSlotsException, WeaponAlreadyPresentException;

    DropInstance grabDrop() throws NotOrdinaryTileException;

    WeaponCard grabWeapon(WeaponCard weaponCard) throws NotSpawnPointTileException;

    boolean missingDrop();

    void addPlayer(Player player);

    void removePlayer(Player player);

    void addAdjacentTiles(Tile tile);

    void setRoom(Room room);

    List<Tile> getTiles(Direction direction, boolean ignoreWalls);

    Direction getDirection(Tile tile);

}
