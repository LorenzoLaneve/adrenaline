package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.lobbyDidStartTimer(connection, this);
    }

}
