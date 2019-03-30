package it.polimi.deib.newdem.adrenaline.common.mechs.items;

import java.util.List;

public class Deck<CardType> {

    // subject to change or restructuring
    List<CardType> list;

    public Deck(List<CardType> list) {
        this.list = list;
        // TODO implement
    }

    public CardType extract(){
        // TODO implement
        return null;
    }

    public void discard(CardType card){
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
