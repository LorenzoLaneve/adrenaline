package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
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
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_EFFECT);
        choices.add(SHADOWSTEP);

        if (visitor.requestPayment(attacker, SLICE_AND_DICE_PAYMENT, SLICE_AND_DICE)) {
            choices.add(SLICE_AND_DICE);
        }


        List<Player> excludedPlayers = new ArrayList<>();

        do {
            Integer choice = visitor.chooseEffect(choices);
            if (choice == null) {
                break;
            }

            switch (choice) {
                case BASIC_EFFECT:
                    Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED,
                            new BlackListFilterPlayerSelector(excludedPlayers, new SameTilePlayerSelector(attacker)));

                    visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2,0));
                    excludedPlayers.remove(redPlayer);
                    break;

                case SHADOWSTEP:
                    Tile destTile = visitor.getTile(new NearTileSelector(attacker, 1,1));

                    visitor.reportGameChange(new MovementGameChange(attacker, destTile));
                    break;

                case SLICE_AND_DICE:
                    Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                            new BlackListFilterPlayerSelector(excludedPlayers, new SameTilePlayerSelector(attacker)));

                    visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 2,0));
                    excludedPlayers.remove(bluePlayer);
                    break;
            }

            choices.remove(choice);
        } while (!choices.isEmpty());

    }

}
