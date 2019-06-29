package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnTileResponse implements UserEvent {

    private TurnView.ValOrUndo<TilePosition> tile;

    public TurnTileResponse(TurnView.ValOrUndo<TilePosition> tile) {
        this.tile = tile;
    }

    public TurnView.ValOrUndo<TilePosition> getValue() {
        return tile;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
