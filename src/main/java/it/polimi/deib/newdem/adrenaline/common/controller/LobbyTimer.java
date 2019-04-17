package it.polimi.deib.newdem.adrenaline.common.controller;

public class LobbyTimer {

    private LobbyTimerListener listener;

    private int secondsLeft;

    private int timerDuration;

    private int syncPeriod;

    private boolean abortRequested;

    /**
     * Initializes the timer instance.
     * @param syncPeriod The period in seconds between two synchronization callbacks.
     * @throws IllegalArgumentException if syncPeriod is non-positive or if the listener is null.
     * @apiNote Only one listener per timer is permitted.
     */
    public LobbyTimer(int syncPeriod, LobbyTimerListener lst) {
        if (syncPeriod <= 0 || lst == null) {
            throw new IllegalArgumentException("The sync period of the timer must be positive and the lst must be non-null.");
        }

        this.syncPeriod = syncPeriod;
        this.listener = lst;
    }

    /**
     * Starts the timer.
     * @param seconds The seconds left to the end of the countdown.
     * @implNote The current thread will be responsible to do the countdown for the timer,
     * the synchronization callbacks will be called on a separate thread, while the end and start events will be called on the current thread.
     * @throws InterruptedException if the thread is interrupted while the timer is counting.
     * @throws IllegalArgumentException if seconds is non-positive.
     */
    public void start(int seconds) throws InterruptedException {
        if (seconds <= 0) {
            throw new IllegalArgumentException("The seconds left for the countdown must be a valid positive integer value.");
        }

        synchronized (this) {
            this.abortRequested = false;
            this.secondsLeft = timerDuration;
        }
        listener.timerWillStart();

        while (secondsLeft > 0) {
            Thread.sleep(1000);

            if (abortRequested) {
                return;
            }

            synchronized (this) {
                secondsLeft -= 1;

                if (syncPeriod > 0 || (timerDuration - secondsLeft) % syncPeriod == 0) {
                    new Thread(() -> listener.timerSync(secondsLeft)).start();
                }
            }
        }

        listener.timerDidFinish();
    }

    /**
     * Aborts the timer, calling {@code timerDidAbort()} on the associated listener.
     * @implNote the listener callback is called on the thread that called this method.
     */
    public void abort() {
        synchronized (this) {
            abortRequested = true;
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
     * Returns the seconds left for the timer to end its countdown.
     */
    public int getSecondsLeft(){
        return secondsLeft;
    }

}
