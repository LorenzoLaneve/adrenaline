package it.polimi.deib.newdem.adrenaline.controller;

/**
 * A Timer object calls methods on a given TimerListener object with time criteria.
 */
public class Timer {

    private TimerListener listener;

    private Thread countingThread;

    private int secondsLeft;

    private int timerDuration;

    private int syncPeriod;

    private boolean isOver;

    private boolean paused;

    /**
     * Initializes the timer instance.
     * @param syncPeriod The period in seconds between two synchronization callbacks.
     * @throws IllegalArgumentException if syncPeriod is non-positive or if the listener is null.
     * @apiNote Only one listener per timer is permitted.
     */
    public Timer(int syncPeriod, TimerListener lst) {
        if (syncPeriod <= 0 || lst == null) {
            throw new IllegalArgumentException("The sync period of the timer must be positive and the lst must be non-null.");
        }

        this.syncPeriod = syncPeriod;
        this.listener = lst;
    }

    /**
     * Starts the timer.
     * @param seconds The seconds left to the end of the countdown.
     * @implNote The countdown for the timer and all the callbacks for TimerListener will be called on a separate thread.
     * @throws IllegalArgumentException if seconds is non-positive.
     */
    public void start(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("The seconds left for the countdown must be a valid positive integer value.");
        }

        this.timerDuration = seconds;

        countingThread = new Thread(this::doCountdown);
        countingThread.start();
    }

    private void doCountdown() {
        try {
            synchronized (this) {
                this.isOver = false;
                this.paused = false;
                this.secondsLeft = timerDuration;
            }
            listener.timerWillStart(timerDuration);

            while (secondsLeft > 0) {
                Thread.sleep(1000);

                if (isOver) {
                    return;
                }

                synchronized (this) {
                    while (paused) wait();
                }

                synchronized (this) {
                    secondsLeft -= 1;

                    if (syncPeriod > 0 || (timerDuration - secondsLeft) % syncPeriod == 0) {
                        new Thread(() -> listener.timerSync(secondsLeft)).start();
                    }
                }
            }

            synchronized (this) {
                isOver = true;
            }

            listener.timerDidFinish();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
        } finally {
            countingThread = null;
        }
    }

    /**
     * Aborts the timer, calling {@code timerDidAbort()} on the associated listener.
     * @implNote the listener callback is called on the thread that called this method.
     */
    public void abort() {
        synchronized (this) {
            isOver = true;
        }

        listener.timerDidAbort();
    }

    /**
     * Forces the timer to the given countdown length.
     */
    public void reset(int seconds){
        this.secondsLeft = seconds;
    }

    /**
     * Returns whether the timer countdown is over.
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * Returns the seconds left for the timer to end its countdown.
     */
    public int getSecondsLeft(){
        return secondsLeft;
    }

    /**
     * Puts the timer in pause state, freezing the countdown until {@code Timer#resume()} is called.
     * Calling this method on a Timer in pause state will cause the discharge to be ignored.
     */
    public void pause() {
        this.paused = true;
    }

    /**
     * Resumes a timer that previously switched to a pause state by {@code Timer#pause()}.
     * Calling this method on an ongoing Timer will cause the discharge to be ignored.
     */
    public synchronized void resume() {
        this.paused = false;
        notifyAll();
    }

}
