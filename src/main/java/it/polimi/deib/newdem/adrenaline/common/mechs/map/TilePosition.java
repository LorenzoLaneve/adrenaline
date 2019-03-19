package it.polimi.deib.newdem.adrenaline.common.mechs.map;

public class TilePosition {

    int x, y;

    // TODO validate
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
}
