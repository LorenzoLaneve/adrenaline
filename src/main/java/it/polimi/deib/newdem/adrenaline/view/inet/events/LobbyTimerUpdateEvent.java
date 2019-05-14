package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class LobbyTimerUpdateEvent implements UserEvent {

    private int seconds;

    /**
     * Create a new event indicating that the timer is being updated (server bound event)
     * @param seconds The seconds left on the timer.
     */
    public LobbyTimerUpdateEvent(int seconds) {
        this.seconds = seconds;
    }

    public int getSecondsLeft() {
        return seconds;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.lobbyDidUpdateTimer(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendLobbyTimerUpdateEvent(this);
    }
}
