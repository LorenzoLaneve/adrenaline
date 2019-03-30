package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

import java.util.List;

public class VisiblePlayerSelectorFactory implements PlayerSelectorFactory {

    MetaPlayer sourcePlayer;

    public VisiblePlayerSelectorFactory(MetaPlayer sourcePlayer){
        //TODO

    }

    @Override
    public PlayerSelector makeSelector(Action action, List<Player> excluded) {
        //TODO

        return null;
    }
}
