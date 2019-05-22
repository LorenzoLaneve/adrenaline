package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class PlayerNameEvent implements UserEvent {

    private PlayerColor playerColor;

    private String newName;

    public PlayerNameEvent(PlayerColor playerColor, String newName) {
        this.playerColor = playerColor;
        this.newName = newName;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public String getNewName() {
        return newName;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidUpdateUsername(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerNameEvent(this);
    }

}
