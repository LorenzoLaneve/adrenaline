package it.polimi.deib.newdem.adrenaline.common.controller;

public interface LobbyTimerListener {

    void timerWillStart();

    void timerSync(int secondsLeft);

    void timerDidFinish();

    void timerDidAbort();

}
