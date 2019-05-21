package it.polimi.deib.newdem.adrenaline.model.game;

import java.util.*;

public class RoundRobin<T> {

    private Deque<T> queue;

    public RoundRobin(List<T> elements) {
        this.queue = new ArrayDeque<>(elements);
    }

    public RoundRobin() {
        this.queue = new ArrayDeque<>();
    }

    public T next() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(T element) {
        if(null != element) {
            queue.addLast(element);
        }
    }

    public void enqueueFirst(T element) {
        queue.addFirst(element);
    }

    public List<T> getList() {
        return new ArrayList<>(queue);
    }
}
