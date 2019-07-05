package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * The server is requesting the user to choose a player among the given ones, to be bound to the given meta player.
 * if {@code forceChoice} is true, then a {@code null} will not be accepted from the server.
 * @see UserEvent to see what this class is used for.
 */
public class TurnPlayerRequest implements UserEvent {

    private MetaPlayer metaPlayer;
    private ArrayList<PlayerColor> legalPlayers;
    private boolean forceChoice;

    public TurnPlayerRequest(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice) {
        this.metaPlayer = metaPlayer;
        this.legalPlayers = new ArrayList<>(legalPlayers);
        this.forceChoice = forceChoice;
    }

    public MetaPlayer getMetaPlayer() {
        return metaPlayer;
    }

    public List<PlayerColor> getSelectablePlayers() {
        return new ArrayList<>(legalPlayers);
    }

    public boolean isChoiceForced() {
        return forceChoice;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }
}
