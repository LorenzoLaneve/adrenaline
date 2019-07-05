package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.KillTrackView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackAddKillEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackDataEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.KillTrackUndoKillEvent;

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
