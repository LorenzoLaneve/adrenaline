package it.polimi.deib.newdem.adrenaline.controller;

public interface LobbyTimerListener {

    void timerWillStart(int secondsLeft);

    void timerSync(int secondsLeft);

    void timerDidFinish();

    void timerDidAbort();

}
