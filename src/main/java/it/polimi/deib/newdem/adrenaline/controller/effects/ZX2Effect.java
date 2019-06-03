package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ZX2Effect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int SCANNER_MODE = 2;

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_MODE);
        choices.add(SCANNER_MODE);

        Player redPlayer, bluePlayer, greenPlayer;
        switch (visitor.chooseEffect(choices)) {
            case BASIC_MODE:
                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));

                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1,2));
                break;

            case SCANNER_MODE:
                List<Player> excludedPlayers = new ArrayList<>();

                redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));
                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 0,1));

                excludedPlayers.add(redPlayer);
                bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                        new BlackListFilterPlayerSelector(excludedPlayers, new VisiblePlayerSelector(attacker))
                );

                if (bluePlayer != null) {
                    visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 0, 1));
                    excludedPlayers.add(bluePlayer);

                    greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN,
                            new BlackListFilterPlayerSelector(excludedPlayers, new VisiblePlayerSelector(attacker))
                    );

                    if (greenPlayer != null) {
                        visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 0, 1));
                    }
                }

                break;
        }

    }
}
