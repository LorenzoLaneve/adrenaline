package it.polimi.deib.newdem.adrenaline.model.game.turn;

import java.util.*;

/**
 * Holds a queue of elements to be executed in order.
 *
 * An element may add zero or more new element to this {@code RoundRobin} during its execution.
 *
 * This class is used by {@code GameImpl} to store a queue of {@code Turn}s.
 *
 * @see it.polimi.deib.newdem.adrenaline.model.game.GameImpl
 * @see Turn
 * @param <T> type of the element cotained in this {@code RoundRobin}
 */
public class RoundRobin<T> {

    private Deque<T> queue;

    /**
     * Creates a new round robin initialized with the given elements in the order
     * in which they are in the given list
     * @param elements to initialize the round robin with
     */
    public RoundRobin(List<T> elements) {
        this.queue = new ArrayDeque<>(elements);
    }

    /**
     * Creates a nre empty round robin
     */
    public RoundRobin() {
        this.queue = new ArrayDeque<>();
    }

    /**
     * Retrieves and removes the first element of this round robin
     * @return first element
     */
    public T next() {
        return queue.poll();
    }

    /**
     * Checks whether this round robin is empty
     * @return is empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Adds an element to the end of this round robin
     * @param element added to the end
     */
    public void enqueue(T element) {
        if(null != element) {
            queue.addLast(element);
        }
    }

    /**
     * Adds an element to the start of this round robin
     * @param element added to the front
     */
    public void enqueueFirst(T element) {
        queue.addFirst(element);
    }

    /**
     * Creates a new list with all the elements currently in this round robin
     *
     * @return list of elements currently in this round robin
     */
    public List<T> getList() {
        return new ArrayList<>(queue);
    }
}
