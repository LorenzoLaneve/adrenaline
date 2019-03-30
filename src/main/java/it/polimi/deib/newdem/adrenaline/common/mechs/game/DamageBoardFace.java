package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.DamageBoard;

import java.util.List;

public interface DamageBoardFace {

    DamageBoard getBoard();

    List<ActionFactory> getAdditionalMoves();
}
