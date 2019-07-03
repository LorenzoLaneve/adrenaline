package it.polimi.deib.newdem.adrenaline.model.game.turn;

public class IndexOrId {
    private int val;
    private boolean isIndex;

    public IndexOrId(int val, boolean isIndex) {
        this.val = val;
        this.isIndex = isIndex;
    }

    public int getVal() {
        return val;
    }

    public boolean isIndex() {
        return isIndex;
    }
}
