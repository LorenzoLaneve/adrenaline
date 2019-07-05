package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.KillTrackView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackAddKillEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackDataEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackUndoKillEvent;

/**
 * A virtual view is a view object that acts as an adapter between model/controller and views,
 * translating the model objects into plain data objects usable by views.
 * This way the view is completely separated from the model and we do not need to clone/reflect
 * model objects into the client.
 *
 * Note: the VirtualGameView is also used to give information about the in-game users to the other
 * virtual views.
 */
public class VirtualKillTrackView implements KillTrackView, KillTrackListener {

    private VirtualGameView vgv;

    public VirtualKillTrackView(VirtualGameView vgv) {
        this.vgv = vgv;
    }

    @Override
    public void killTrackDidRestore(KillTrackData data) {
        restoreView(data);
    }

    @Override
    public void playerDidKill(Player player, int amount) {
        registerKill(player.getColor(), amount);
    }

    @Override
    public void restoreView(KillTrackData data) {
        vgv.sendEvent(new KillTrackDataEvent(data));
    }

    @Override
    public void registerKill(PlayerColor pColor, int amount) {
        vgv.sendEvent(new KillTrackAddKillEvent(pColor, amount));
    }

    @Override
    public void undoLastKill() {
        vgv.sendEvent(new KillTrackUndoKillEvent());
    }

}
