package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class CyberbladeEffect implements Effect {

    private static final int BASIC_EFFECT = 1;

    private static final int SHADOWSTEP = 2;

    private static final int SLICE_AND_DICE = 3;

    private static final PaymentInvoice SLICE_AND_DICE_PAYMENT = new PaymentInvoice(0,0,1,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_EFFECT);
        choices.add(SHADOWSTEP);

        if (manager.pay(SLICE_AND_DICE, SLICE_AND_DICE_PAYMENT)) {
            choices.add(SLICE_AND_DICE);
        }

        EffectSwitch.create(choices)
                .when(BASIC_EFFECT, this::basicEffect)
                .when(SHADOWSTEP, this::shadowstep)
                .when(SLICE_AND_DICE, this::sliceAndDice)
                .execute(manager, actor);

    }


    private void basicEffect(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new SameTilePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 2,0);
    }

    private void shadowstep(EffectManager manager, Player actor) throws UndoException {
        Tile destTile = manager.bindTile(new NearTileSelector(actor, 1, 1));

        manager.movePlayer(actor, destTile);
    }

    private void sliceAndDice(EffectManager manager, Player actor) throws UndoException {
        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new SameTilePlayerSelector(actor));

        manager.damagePlayer(actor, bluePlayer, 2, 0);
    }

}
