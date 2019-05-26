package it.polimi.deib.newdem.adrenaline.model.items;

import java.util.*;

public class DropDeck<DropInstance> {
    private Deque<DropInstance> drawableCards;
    private HashSet<DropInstance> discardedCards;
    private HashSet<DropInstance> activeCards;

    /** Creates a new {@code Deck} made up of the elements of the given list.
     *
     * @throws IllegalArgumentException {@code list} is null or empty
     * @param list the elements to build a {@code Deck} from. This may have repetitions.
     *             Not null, not empty.
     */
    public DropDeck(List<DropInstance> list) {
        if(null == list || list.isEmpty()) {
            throw new IllegalArgumentException("Invalid parameter list");
        }

        ArrayList<DropInstance> workingList = new ArrayList<>(list);
        Collections.shuffle(workingList);

        this.drawableCards = new LinkedList<>(workingList);
        this.discardedCards = new HashSet<>();
        this.activeCards = new HashSet<>();
    }


    /** Draws a card from this deck.
     *
     * If no card is drawable, the discard pile is shuffled into the deck,
     * then a card is drawn.
     *
     * @return the drawn card
     */
    public DropInstance draw() throws NoDrawableCardException {
        if(this.isEmpty()) {
            reshuffle();
        }

        if(this.isEmpty()) {
            throw new NoDrawableCardException();
        }

        DropInstance drawnCard = drawableCards.pop();
        activeCards.add(drawnCard);
        return drawnCard;
    }

    /** Puts the given {@code card} into this deck's discard pile.
     *
     * @throws IllegalArgumentException the given {@code card} does not belong to this deck.
     * @param card the card to discard. Not null, must belong to this deck.
     */
    public void discard(DropInstance card) {
        if(!activeCards.contains(card)) {
            throw new IllegalArgumentException("This card does not belong to this deck");
        }

        activeCards.remove(card);
        discardedCards.add(card);
    }

    /** Checks for the presence of drawable cards.
     *
     * @return whether or not the deck is empty
     */
    public boolean isEmpty() {
        return drawableCards.isEmpty();
    }

    /** Shuffles this deck's discard pile together with the main deck.
     *
     */
    public void reshuffle() {
        ArrayList<DropInstance> workingList = new ArrayList<>();

        workingList.addAll(discardedCards);
        workingList.addAll(drawableCards);
        Collections.shuffle(workingList);

        this.drawableCards = new LinkedList<>(workingList);
    }
}