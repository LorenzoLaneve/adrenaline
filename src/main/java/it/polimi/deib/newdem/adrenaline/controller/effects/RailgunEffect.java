package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;

/**
 * Card effect that implements Adrenaline' Railgun weapon card.
 * @see Effect for further information about the card effects.
 */
public class RailgunEffect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int PIERCING_MODE = 2;


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        EffectSwitch.create(BASIC_MODE, PIERCING_MODE)
                .when(BASIC_MODE, this::basicMode)
                .when(PIERCING_MODE, this::piercingMode)
                .executeOne(manager, actor);

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new DirectionalPlayerSelector(actor, true));

        manager.damagePlayer(actor, redPlayer, 3 ,0);
    }

    private void piercingMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new DirectionalPlayerSelector(actor, true));

        Direction comboDirection = actor.getTile().getDirection(redPlayer.getTile());

        manager.damagePlayer(actor, redPlayer, 2, 0);

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new DirectionalPlayerSelector(actor, comboDirection, true));
        manager.damagePlayer(actor, bluePlayer, 2, 0);
    }

}
