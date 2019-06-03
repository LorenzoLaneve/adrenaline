package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class ShotgunEffect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int LONG_BARREL_MODE = 2;

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_MODE);
        choices.add(LONG_BARREL_MODE);

        Player redPlayer;
        switch (visitor.chooseEffect(choices)) {
            case BASIC_MODE:
                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 0,0));

                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3, 0));

                Tile destTile = visitor.getTile(new NearTileSelector(attacker, 0, 1));

                visitor.reportGameChange(new MovementGameChange(redPlayer, destTile));
                break;

            case LONG_BARREL_MODE:
                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 1,1));

                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));
                break;
            default: break;
        }

    }

}
