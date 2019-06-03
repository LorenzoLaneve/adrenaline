package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisibleTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class GrenadeLauncherEffect implements Effect {

    private static final int MOVE_TARGET = 1;

    private static final int EXTRA_GRENADE = 2;

    private static final PaymentInvoice EXTRA_GRENADE_PAYMENT = new PaymentInvoice(1,0,0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1,0));


        List<Integer> choices = new ArrayList<>();
        choices.add(MOVE_TARGET);
        choices.add(EXTRA_GRENADE);

        Tile targetTile;
        do {
            Integer choice = visitor.chooseEffect(choices);
            if (choice == null) {
                break;
            }

            switch (choice) {
                case MOVE_TARGET:
                    targetTile = visitor.getTile(new NearTileSelector(redPlayer, 0, 1));

                    visitor.reportGameChange(new MovementGameChange(redPlayer, targetTile));
                    break;

                case EXTRA_GRENADE:
                    targetTile = visitor.getTile(new VisibleTileSelector(attacker));

                    for (Player player : targetTile.getPlayers()) if (attacker != player) {
                        visitor.reportGameChange(new DamageGameChange(attacker, player, 1, 0));
                    }

                    break;
                default: break;
            }

            choices.remove(choice);
        } while (choices.isEmpty());

    }
}
