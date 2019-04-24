package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.ArrayList;
import java.util.List;

public class ConcreteRoom implements Room {

    private Map map;

    private ArrayList<Tile> tiles;

    private RoomTileSeeds roomTileSeeds;

    public ConcreteRoom(Map map, RoomTileSeeds roomTileSeeds) {
        this.map = map;
        this.roomTileSeeds = roomTileSeeds;
        this.tiles = new ArrayList<>();
    }

    @Override
    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }

    @Override
    public Map getMap() {
        //TODO how not to send map
        return map;
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();

        for (Tile x : this.getTiles()){
            playerList.addAll(x.getPlayers());
        }
        return playerList;
    }

    @Override
    public RoomTileSeeds getRoomTileSeeds() {
        return new RoomTileSeeds(roomTileSeeds.getCornerTopLeft(),
                roomTileSeeds.getCornerTopRight(),
                roomTileSeeds.getCornerBottomRight(),
                roomTileSeeds.getCornerBottomLeft(),
                roomTileSeeds.getSpawnPointTilePosition());
    }

    @Override
    public void generateTiles() {
        int leftX = roomTileSeeds.getLeftX();
        int rightX = roomTileSeeds.getRightX();
        int topY = roomTileSeeds.getTopY();
        int bottomY = roomTileSeeds.getBottomY();

        for(int y = bottomY; y <= topY; y++){
            for (int x = leftX; x <= rightX; x++){
                if(x == roomTileSeeds.getSpawnPointTilePosition().getX() && y == roomTileSeeds.getSpawnPointTilePosition().getY()) {
                    SpawnPointTile newTile = new SpawnPointTile(this, new TilePosition(x, y));
                    tiles.add(newTile);
                }
                else{
                    tiles.add(new OrdinaryTile(this, new TilePosition(x, y)));
                }
            }

        }
    }


}
