package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;
import it.polimi.deib.newdem.adrenaline.view.client.TurnInterruptedException;

/**
 * Object that is used to block the client thread to wait for UI events.
 * Type {@code T} is the type of the value the UI should deliver to the waiting thread.
 */
public class GUILocker<T> {

    private T value;

    private boolean shouldInterrupt;

    /**
     * Creates a new empty GUI locker
     */
    public GUILocker() {
        this.value = null;
        this.shouldInterrupt = false;
    }

    /**
     * Blocks this thread until the UI delivers data, which is then returned by this method.
     * @throws ClosedException if another thread called {@code GUILocker#interrupt()}.
     */
    public synchronized T waitForValue() {

        try {
            while (!shouldInterrupt && value == null) {
                wait();
            }

            if (shouldInterrupt) {
                throw new TurnInterruptedException();
            }

        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

        return value;
    }

    /**
     * Delivers the given value to the locker and notifies the waiting thread.
     */
    public synchronized void deliver(T value) {
        this.value = value;
        notifyAll();
    }

    /**
     * Interrupts the waiting thread.
     */
    public synchronized void interrupt() {
        this.shouldInterrupt = true;
        notifyAll();
    }

}
