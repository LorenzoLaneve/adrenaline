package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
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
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        boolean fragmentingWarhead = visitor.requestPayment(attacker, FRAGMENTING_WARHEAD_PAYMENT, FRAGMENTING_WARHEAD);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_EFFECT);

        if (visitor.requestPayment(attacker, ROCKET_JUMP_PAYMENT, ROCKET_JUMP)) {
            choices.add(ROCKET_JUMP);
        }

        Tile targetTile;
        do {
            Integer choice = visitor.chooseEffect(choices);
            if (choice == null) {
                break;
            }

            switch (choice) {
                case BASIC_EFFECT:
                    Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new IntersectPlayerSelector(
                            new VisiblePlayerSelector(attacker),
                            new NearPlayerSelector(attacker, 1, 100)
                    ));

                    visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

                    if (fragmentingWarhead) {
                        for (Player player : redPlayer.getTile().getPlayers()) {
                            visitor.reportGameChange(new DamageGameChange(attacker, player, 1, 0));
                        }
                    }

                    targetTile = visitor.getTile(new NearTileSelector(redPlayer, 0, 1));

                    visitor.reportGameChange(new MovementGameChange(redPlayer, targetTile));
                    break;

                case ROCKET_JUMP:
                    targetTile = visitor.getTile(new NearTileSelector(attacker, 0, 2));

                    visitor.reportGameChange(new MovementGameChange(attacker, targetTile));

                    break;
                default: break;
            }

            choices.remove(choice);
        } while (choices.isEmpty());

    }

}
