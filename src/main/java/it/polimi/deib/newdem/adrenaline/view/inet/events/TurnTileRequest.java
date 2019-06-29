package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class TurnTileRequest implements UserEvent {

    private ArrayList<TilePosition> legalTiles;
    private boolean forceChoice;

    public TurnTileRequest(List<TilePosition> legalTiles, boolean forceChoice) {
        this.legalTiles = new ArrayList<>(legalTiles);
        this.forceChoice = forceChoice;
    }

    public List<TilePosition> getSelectableTiles() {
        return new ArrayList<>(legalTiles);
    }

    public boolean isChoiceForced() {
        return forceChoice;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }
}
