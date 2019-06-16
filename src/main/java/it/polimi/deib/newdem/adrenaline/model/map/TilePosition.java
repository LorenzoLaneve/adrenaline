package it.polimi.deib.newdem.adrenaline.model.map;

import java.io.Serializable;
import java.util.Objects;

public class TilePosition implements Serializable {

    private int x;
    private int y;

    public TilePosition(int x, int y) {

        if(x<0 || y<0 ){
            throw new IllegalArgumentException();
        }
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

    public TilePosition move(Direction direction) {
        switch (direction){
            case EAST:
                return new TilePosition(this.getX()+1,this.getY());
            case WEST:
                return new TilePosition(this.getX()-1, this.getY());
            case NORTH:
                return new TilePosition(this.getX(), this.getY()+1);
            case SOUTH:
                return new TilePosition(this.getX(), this.getY()-1);
            default:
                throw new IllegalArgumentException();
        }
    }
}
