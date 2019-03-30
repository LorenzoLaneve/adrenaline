package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;

import java.util.List;

public interface ActionBoard {

    List<ActionFactory> getBasicActions();

    int getIterations();

}
