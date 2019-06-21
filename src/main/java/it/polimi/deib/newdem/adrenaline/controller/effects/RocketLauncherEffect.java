package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class RocketLauncherEffect implements Effect {

    private static final int BASIC_EFFECT = 1;

    private static final int ROCKET_JUMP = 2;

    private static final int FRAGMENTING_WARHEAD = 3;

    private static final PaymentInvoice ROCKET_JUMP_PAYMENT = new PaymentInvoice(0,1,0,0);

    private static final PaymentInvoice FRAGMENTING_WARHEAD_PAYMENT = new PaymentInvoice(0,0,1,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        boolean fragmentingWarhead = manager.pay(FRAGMENTING_WARHEAD, FRAGMENTING_WARHEAD_PAYMENT);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_EFFECT);

        if (manager.pay(ROCKET_JUMP, ROCKET_JUMP_PAYMENT)) {
            choices.add(ROCKET_JUMP);
        }

        EffectSwitch.create(choices)
                .when(BASIC_EFFECT, (m, a) -> basicMode(m, a, fragmentingWarhead))
                .when(ROCKET_JUMP, this::rocketJumpMode)
                .execute(manager, actor);

    }

    private void basicMode(EffectManager manager, Player actor, boolean fragmentingWarhead) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new IntersectPlayerSelector(
                new VisiblePlayerSelector(actor),
                new NearPlayerSelector(actor, 1, 100)
        ));

        manager.damagePlayer(actor, redPlayer, 2, 0);

        if (fragmentingWarhead) {
            for (Player player : redPlayer.getTile().getPlayers()) {
                manager.damagePlayer(actor, player, 1, 0);
            }
        }

        Tile targetTile = manager.bindTile(new NearTileSelector(redPlayer, 0, 1));

        manager.movePlayer(redPlayer, targetTile);
    }

    private void rocketJumpMode(EffectManager manager, Player actor) throws UndoException {
        Tile targetTile = manager.bindTile(new NearTileSelector(actor, 0, 2));

        manager.movePlayer(actor, targetTile);
    }
}
