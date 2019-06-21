package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ZX2Effect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int SCANNER_MODE = 2;


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        EffectSwitch.create(BASIC_MODE, SCANNER_MODE)
                .when(BASIC_MODE, this::basicMode)
                .when(SCANNER_MODE, this::scannerMode)
                .executeOne(manager, actor);

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 1,2);
    }

    private void scannerMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));
        manager.damagePlayer(actor, redPlayer, 0,1);

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(actor));

        if (bluePlayer != null) {
            manager.damagePlayer(actor, bluePlayer, 0, 1);

            Player greenPlayer = manager.bindPlayer(MetaPlayer.GREEN, new VisiblePlayerSelector(actor));
            if (greenPlayer != null) {
                manager.damagePlayer(actor, greenPlayer, 0, 1);
            }
        }
    }

}
