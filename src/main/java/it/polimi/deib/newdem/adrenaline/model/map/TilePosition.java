package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof TilePosition)) {
            return false;
        }
        TilePosition tilePosition = (TilePosition) o;
        return x == tilePosition.getX() && y == tilePosition.getY();
    }
}
