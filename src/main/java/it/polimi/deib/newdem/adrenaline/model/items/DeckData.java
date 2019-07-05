package it.polimi.deib.newdem.adrenaline.model.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeckData implements Serializable {

    /**
     * Data representation of the current deck state. Intended for communication with the views.
     */

    int drawableCards;
    List<Integer> discardedCards;

    DeckData(int drawableCards, List<Integer> discardedCardIDs) {
        this.drawableCards = drawableCards;
        this.discardedCards = discardedCardIDs;
        }

    public int getDrawableCards() {
        return drawableCards;
    }

    public List<Integer> getDiscardedCards() {
        return discardedCards;
    }
}
