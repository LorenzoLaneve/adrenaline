package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class PlayerScoreEvent implements UserEvent {

    private PlayerColor color;

    private int score;

    public PlayerScoreEvent(PlayerColor color, int score) {
        this.color = color;
        this.score = score;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    public int getNewScore() {
        return score;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidUpdateScore(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendPlayerScoreEvent(this);
    }

}
