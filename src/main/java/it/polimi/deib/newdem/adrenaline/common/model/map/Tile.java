package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

public interface Tile {

    Map getMap();

    Room getRoom();

    TilePosition getPosition();

    int distanceFrom(Tile t);

    List<Player> getPlayers();

    List<Tile> getAdjacentTiles();

    boolean hasSpawnPoint();

    DropInstance inspectDrop();

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

}
