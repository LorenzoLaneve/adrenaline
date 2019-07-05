package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The timer in the lobby has started with the given seconds of countdown.
 * @see UserEvent to see what this class is used for.
 */
public class LobbyTimerStartEvent implements UserEvent {

    private int seconds;

    /**
     * Create a new event indicating that the lobby has started a new timer (server bound event)
     * @param seconds The seconds left on the timer.
     */
    public LobbyTimerStartEvent(int seconds) {
        this.seconds = seconds;
    }

    public int getSecondsLeft() {
        return seconds;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
