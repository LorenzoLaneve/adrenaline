package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KillTrackData implements Serializable{

    private ArrayList<Cell> cells;
    private final int initialLength;

    KillTrackData(KillTrack killTrack) {
        initialLength = killTrack.getTrackLength();
        cells = new ArrayList<>();
    }

    void addCell(Cell cell){
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return new ArrayList<>(cells);
    }

    public int getInitialLength() {
        return initialLength;
    }
}
