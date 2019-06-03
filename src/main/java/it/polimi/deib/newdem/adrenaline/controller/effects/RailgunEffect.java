package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;

import java.util.ArrayList;
import java.util.List;

public class RailgunEffect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int PIERCING_MODE = 2;


    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_MODE);
        choices.add(PIERCING_MODE);

        Player redPlayer, bluePlayer;
        switch (visitor.chooseEffect(choices)) {
            case BASIC_MODE:
                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new DirectionalPlayerSelector(attacker, true));

                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3 ,0));
                break;

            case PIERCING_MODE:
                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new DirectionalPlayerSelector(attacker, true));

                Direction comboDirection = attacker.getTile().getDirection(redPlayer.getTile());

                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

                List<Player> excludedPlayers = new ArrayList<>();
                excludedPlayers.add(redPlayer);

                bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                        new BlackListFilterPlayerSelector(excludedPlayers, new DirectionalPlayerSelector(attacker, comboDirection, true)));

                visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 2,0));

                break;

        }

    }

}
