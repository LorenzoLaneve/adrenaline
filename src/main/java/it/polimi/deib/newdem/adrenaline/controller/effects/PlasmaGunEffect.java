package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Card effect that implements Adrenaline' Plasma Gun weapon card.
 * @see Effect for further information about the card effects.
 */
public class PlasmaGunEffect implements Effect {

    private static final int BASIC_EFFECT = 1;

    private static final int PHASE_GLIDE = 2;

    private static final int CHARGED_SHOT = 3;

    private static final PaymentInvoice CHARGED_SHOT_PAYMENT = new PaymentInvoice(0,1,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        EffectSwitch.create(BASIC_EFFECT, PHASE_GLIDE)
                .when(BASIC_EFFECT, this::basicMode)
                .when(PHASE_GLIDE, this::phaseGlide)
                .execute(manager, actor);

        Player redPlayer = manager.getPlayer(MetaPlayer.RED);
        if (redPlayer != null && manager.pay(CHARGED_SHOT, CHARGED_SHOT_PAYMENT)) {
            manager.damagePlayer(actor, redPlayer, 1, 0);
        }
    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 2,0);
    }

    private void phaseGlide(EffectManager manager, Player actor) throws UndoException {
        Tile destTile = manager.bindTile(new NearTileSelector(actor, 0, 2));

        manager.movePlayer(actor, destTile);
    }
}
