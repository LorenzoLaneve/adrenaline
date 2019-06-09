package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TileSelectionEvent implements UserEvent {

    private int x;
    private int y;

    public TileSelectionEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
