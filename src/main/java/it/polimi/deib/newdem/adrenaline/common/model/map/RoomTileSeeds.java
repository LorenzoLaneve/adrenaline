package it.polimi.deib.newdem.adrenaline.common.model.map;

public class RoomTileSeeds {
    private TilePosition cornerTopLeft;
    private TilePosition cornerTopRight;
    private TilePosition cornerBottomRight;
    private TilePosition cornerBottomLeft;
    private TilePosition spawnPointTilePosition;

    public RoomTileSeeds(TilePosition cornerTopLeft,
                         TilePosition cornerTopRight,
                         TilePosition cornerBottomRight,
                         TilePosition cornerBottomLeft,
                         TilePosition spawnPointTilePosition){

        boolean yCondition = ((cornerTopLeft.getY() == cornerTopRight.getY()) &&
                (cornerBottomLeft.getY() == cornerBottomRight.getY()) &&
                (cornerTopLeft.getY() >= cornerBottomRight.getY()));

        boolean xCondition = ((cornerTopLeft.getX() == cornerBottomLeft.getX())&&
                (cornerBottomRight.getX() == cornerTopRight.getX())&&
                cornerBottomLeft.getX() <= cornerBottomRight.getX());

        int leftX = cornerTopLeft.getX();
        int rightX = cornerTopRight.getX();
        int topY = cornerTopRight.getY();
        int bottomY = cornerBottomRight.getY();

        boolean containsSpawnPoint = ((spawnPointTilePosition.getY() >= bottomY && spawnPointTilePosition.getY() <= topY) &&
                ( spawnPointTilePosition.getX() >= leftX && spawnPointTilePosition.getX() <= rightX));

        if(yCondition && xCondition && containsSpawnPoint){
            this.cornerTopLeft = cornerTopLeft;
            this.cornerTopRight = cornerTopRight;
            this.cornerBottomRight = cornerBottomRight;
            this.cornerBottomLeft = cornerBottomLeft;
            this.spawnPointTilePosition = spawnPointTilePosition;
        }
        else{
            throw new IllegalArgumentException("The tiles seeds are mismatched");
        }
    }

    public TilePosition getSpawnPointTilePosition() {
        return spawnPointTilePosition;
    }

    public TilePosition getCornerBottomLeft() {
        return cornerBottomLeft;
    }

    public TilePosition getCornerTopLeft() {
        return cornerTopLeft;
    }

    public TilePosition getCornerTopRight() {
        return cornerTopRight;
    }

    public TilePosition getCornerBottomRight() {
        return cornerBottomRight;
    }

    public boolean containsTilePosition(TilePosition tilePosition){
        int tileX = tilePosition.getX();
        int tileY = tilePosition.getY();

        int leftX = cornerTopLeft.getX();
        int rightX = cornerTopRight.getX();
        int topY = cornerTopRight.getY();
        int bottomY = cornerBottomRight.getY();

        return ((tileY >= bottomY && tileY <= topY) && ( tileX >= leftX && tileX <= rightX));
    }

    public int getLeftX(){
        return cornerTopLeft.getX();
    }

    public int getRightX(){
        return cornerTopRight.getX();
    }

    public int getTopY(){
        return cornerTopRight.getY();
    }

    public int getBottomY(){
        return cornerBottomRight.getY();
    }


}
