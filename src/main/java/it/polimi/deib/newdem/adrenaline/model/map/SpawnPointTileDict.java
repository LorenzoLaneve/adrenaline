package it.polimi.deib.newdem.adrenaline.model.map;

public class SpawnPointTileDict{
    private int[] red;
    private int[] blue;
    private int[] yellow;

    SpawnPointTileDict(int[] red, int[] blue, int[] yellow){
        this.red = red;
        this.blue = blue;
        this.yellow = yellow;
    }

    public int[] getBlue() {
        return blue;
    }

    public int[] getRed() {
        return red;
    }

    public int[] getYellow() {
        return yellow;
    }
}
