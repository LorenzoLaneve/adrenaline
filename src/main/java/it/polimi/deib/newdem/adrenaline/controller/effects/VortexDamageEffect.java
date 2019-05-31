package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisibleTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class VortexDamageEffect extends ConcreteEffect {


    public VortexDamageEffect(int id) {
        super(id);
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Tile vortexTile = visitor.getTile(new VisibleTileSelector(attacker.getTile()));
        // TODO blacklist tile selector, exclude tiles at 0 distance.

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(vortexTile, 0,1));
        if (redPlayer.getTile() != vortexTile) {
            visitor.reportGameChange(new MovementGameChange(redPlayer, vortexTile));
        }
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

        if (visitor.requestPayment(attacker, new PaymentInvoice(1, 0, 0, 0))) {
            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new NearPlayerSelector(vortexTile, 0, 1));
            if (bluePlayer.getTile() != vortexTile) {
                visitor.reportGameChange(new MovementGameChange(bluePlayer, vortexTile));
            }
            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));

            Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN,
                                                        new NearPlayerSelector(vortexTile, 0, 1),
                                                        false);
            if (greenPlayer != null) {
                if (greenPlayer.getTile() != vortexTile) {
                    visitor.reportGameChange(new MovementGameChange(greenPlayer, vortexTile));
                }
                visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 1, 0));
            }
        }

    }
}
