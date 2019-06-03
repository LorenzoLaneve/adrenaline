package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class PlasmaGunEffect implements Effect {

    private static final int BASIC_EFFECT = 1;

    private static final int PHASE_GLIDE = 2;

    private static final int CHARGED_SHOT = 3;

    private static final PaymentInvoice CHARGED_SHOT_PAYMENT = new PaymentInvoice(0,1,0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_EFFECT);
        choices.add(PHASE_GLIDE);

        Player redPlayer = null;
        do {
            Integer choice = visitor.chooseEffect(choices);

            if (choice != null) {
                switch (choice) {
                    case BASIC_EFFECT:
                        redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));

                        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2,0));
                        break;

                    case PHASE_GLIDE:
                        Tile destTile = visitor.getTile(new NearTileSelector(attacker, 0, 2));

                        visitor.reportGameChange(new MovementGameChange(attacker, destTile));
                        break;

                    default: break;
                }

                choices.remove(choice);
            } else {
                break;
            }

        } while (choices.isEmpty());

        if (redPlayer != null) {

            if (visitor.requestPayment(redPlayer, CHARGED_SHOT_PAYMENT, CHARGED_SHOT)) {
                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));
            }

        }


    }

}
