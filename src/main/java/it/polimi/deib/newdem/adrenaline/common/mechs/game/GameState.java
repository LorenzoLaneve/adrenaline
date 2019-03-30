package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.Turn;

public interface GameState {

    Turn makeTurn();

    boolean isInFrenzy();
}
