package it.polimi.deib.newdem.adrenaline.controller;

public interface TimerListener {

    /**
     * The listened timer has started its countdown
     * @param secondsLeft the seconds left until the end of the countdown
     */
    void timerWillStart(int secondsLeft);

    /**
     * The listened timer has sent a timer tick to update the current state of the timer.
     * @param secondsLeft the seconds left until the end of the countdown
     */
    void timerSync(int secondsLeft);

    /**
     * The listened timer hss finished its countdown.
     */
    void timerDidFinish();

    /**
     * The listened timer has been aborted through {@code Timer#abort()}.
     */
    void timerDidAbort();

}
