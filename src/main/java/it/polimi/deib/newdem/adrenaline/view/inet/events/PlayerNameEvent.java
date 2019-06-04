package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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

}
