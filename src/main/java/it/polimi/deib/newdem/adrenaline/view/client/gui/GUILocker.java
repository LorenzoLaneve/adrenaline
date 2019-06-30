package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;
import it.polimi.deib.newdem.adrenaline.view.client.TurnInterruptedException;

public class GUILocker<T> {

    private T value;

    private boolean shouldInterrupt;


    public GUILocker() {
        this.value = null;
        this.shouldInterrupt = false;
    }



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


    public synchronized void deliver(T value) {
        this.value = value;
        notifyAll();
    }

    public synchronized void interrupt() {
        this.shouldInterrupt = true;
        notifyAll();
    }

}
