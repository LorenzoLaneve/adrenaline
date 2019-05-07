package it.polimi.deib.newdem.adrenaline.controller;

public interface LobbyTimerListener {

    void timerWillStart();

    void timerSync(int secondsLeft);

    void timerDidFinish();

    void timerDidAbort();

}
