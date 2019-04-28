package it.polimi.deib.newdem.adrenaline.common.view.server;

import it.polimi.deib.newdem.adrenaline.common.model.game.DamageBoardListener;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.common.view.DamageBoardView;

public class VirtualDamageBoardView implements DamageBoardView, DamageBoardListener {

    @Override
    public void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer) {
        // TODO
    }

    @Override
    public void registerDamage(int damageAmount, PlayerColor dealer) {
        // TODO
    }

    @Override
    public void registerMarks(int markAmount, PlayerColor dealer) {
        // TODO
    }

    @Override
    public void convertMarks(PlayerColor dealer) {
        // TODO
    }

}
