package it.polimi.deib.newdem.adrenaline.common.model.items;

import java.util.List;

public class Deck<T> {

    // subject to change or restructuring
    private List<T> cards;

    /** Creates a new {@code Deck} made up of the elements of the given list
     *
     * @param list the elements to build a deck from
     */
    public Deck(List<T> list) {
        this.cards = list;
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
