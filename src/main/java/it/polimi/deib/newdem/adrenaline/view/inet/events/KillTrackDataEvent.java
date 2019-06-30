package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class KillTrackDataEvent implements UserEvent {

    private KillTrackData data;

    public KillTrackDataEvent(KillTrackData data) {
        this.data = data;
    }

    public KillTrackData getData() {
        return data;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
