package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.MapViewEventListener;
import it.polimi.deib.newdem.adrenaline.view.server.dialogs.Dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerConnectionReceiver {

    private List<MapViewEventListener> mapListeners;

    private HashMap<User, List<Dialog<PlayerColor>>> playerDialogs;

    public ServerConnectionReceiver() {
        this.mapListeners = new ArrayList<>();
    }

    public void addDialog(User user, Dialog<PlayerColor> dialog) {
        List<Dialog<PlayerColor>> dialogs = playerDialogs.get(user);

        if (dialogs == null) {
            dialogs = new ArrayList<>();
            playerDialogs.put(user, dialogs);
        }

        dialogs.add(dialog);
    }

    public void removeDialog(User user, Dialog<PlayerColor> dialog) {
        List<Dialog<PlayerColor>> dialogs = playerDialogs.get(user);

        if (dialogs != null) {
            dialogs.remove(dialog);
        }
    }

    public void addMapEventListener(MapViewEventListener listener) {
        this.mapListeners.add(listener);
    }

}
