package it.polimi.deib.newdem.adrenaline.common.model.items;

import java.util.List;

public class Deck<T> {

    private List<T> drawableCards;
    private List<T> discardedCards;
    private List<T> activeCards;

    /** Creates a new {@code Deck} made up of the elements of the given list.
     *
     * @throws IllegalArgumentException {@code list} is null or empty
     * @param list the elements to build a {@code Deck} from. This may have repetitions.
     *             Not null, not empty.
     */
    public Deck(List<T> list) {
        this.drawableCards = list;
        // TODO implement
    }

    /** Draws a card from this deck.
     *
     * If no card is drawable, the discard pile is shuffled into the deck,
     * then a card is drawn.
     *
     * @return the drawn card
     */
    public T draw() throws NoDrawableCardException {
        // TODO implement
        return null;
    }

    /** Puts the given {@code card} into this deck's discard pile.
     *
     * @throws IllegalArgumentException the given {@code card} does not belong to this deck.
     * @param card the card to discard. Not null, must belong to this deck.
     */
    public void discard(T card) {
        // TODO implement
    }

    /** Checks for the presence of drawable cards.
     *
     * @return whether or not the deck is empty
     */
    public boolean isEmpty() {
        // TODO implement
        return true;
    }

    /** Shuffles this deck's discard pile together with the main deck.
     *
     */
    public void reshuffle() {
        // TODO implement
    }
}
