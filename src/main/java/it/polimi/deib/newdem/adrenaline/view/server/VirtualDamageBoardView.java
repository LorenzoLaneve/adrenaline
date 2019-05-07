package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.DamageBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;

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
