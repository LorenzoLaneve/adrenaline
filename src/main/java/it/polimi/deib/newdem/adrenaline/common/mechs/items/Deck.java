package it.polimi.deib.newdem.adrenaline.common.mechs.items;

import java.util.List;

public class Deck<T> {

    // subject to change or restructuring
    List<T> list;

    public Deck(List<T> list) {
        this.list = list;
        // TODO implement
    }

    public T extract(){
        // TODO implement
        return null;
    }

    public void discard(T card){
        // TODO implement
    }

    public boolean isEmpty() {
        // TODO implement
        return true;
    }

    public void reshuffle() {
        // TODO implement
    }
}
