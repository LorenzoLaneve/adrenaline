package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisibleTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Card effect that implements Adrenaline' Grenade Launcher weapon card.
 * @see Effect for further information about the card effects.
 */
public class GrenadeLauncherEffect implements Effect {

    private static final int MOVE_TARGET = 1;

    private static final int EXTRA_GRENADE = 2;

    private static final PaymentInvoice EXTRA_GRENADE_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));
        manager.damagePlayer(actor, redPlayer, 1, 0);

        List<Integer> choices = new ArrayList<>();
        choices.add(MOVE_TARGET);
        //if (manager.pay(EXTRA_GRENADE, EXTRA_GRENADE_PAYMENT)) {
            choices.add(EXTRA_GRENADE);
        //}

        EffectSwitch.create(choices)
                .when(MOVE_TARGET, (m, a) -> moveTarget(m, a, redPlayer))
                .when(EXTRA_GRENADE, this::extraGrenade)
                .execute(manager, actor);

    }

    private void moveTarget(EffectManager manager, Player actor, Player redPlayer) throws UndoException {
        Tile targetTile = manager.bindTile(new NearTileSelector(redPlayer, 0, 1));

        manager.movePlayer(redPlayer, targetTile);
    }

    private void extraGrenade(EffectManager manager, Player actor) throws UndoException {
        Tile targetTile = manager.bindTile(new VisibleTileSelector(actor));

        for (Player player : targetTile.getPlayers()) {
            if (actor != player) {
                manager.damagePlayer(actor, player, 1, 0);
            }
        }
    }

}
