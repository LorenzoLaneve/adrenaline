package it.polimi.deib.newdem.adrenaline.model.game.turn;

import java.util.ArrayDeque;
import java.util.Deque;

public class WeaponIndexQueue {

    private Deque<Integer> indexQ;
    private Deque<Integer> idQ;
    private Deque<Boolean> isIndexQ;

    public WeaponIndexQueue() {
        indexQ = new ArrayDeque<>();
        idQ = new ArrayDeque<>();
        isIndexQ = new ArrayDeque<>();
    }

    public void pushIndex(int index) {
        indexQ.push(index);
        isIndexQ.push(true);
    }

    public void pushWeaponCardId(int id) {
        idQ.push(id);
        isIndexQ.push(false);
    }

    public IndexOrId pop() {
        if(isIndexQ.pop()) {
            return new IndexOrId(indexQ.pop(), true);
        }
        else {
            return new IndexOrId(idQ.pop(), false);
        }
    }

    public int size() {
        return isIndexQ.size();
    }
}
