package it.polimi.deib.newdem.adrenaline.common.model.map;

public class TilePosition {

    private int x;
    private int y;

    public TilePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equalPosition(TilePosition tilePosition){
        return (this.getY() == tilePosition.getY() && this.getX() == tilePosition.getX());
    }
}
