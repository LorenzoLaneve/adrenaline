package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

public class DamageGameChange implements GameChange {

    private Player attacker;
    private Player attacked;

    private int damageAmount;
    private int markAmount;


    public DamageGameChange(Player attacker, Player attacked, int dmgAmt, int mrkAmt){
        //TODO
    }

    @Override
    public void update(Game game) {
        //TODO
    }

    @Override
    public void revert(Game game) {
        //TODO
    }
}
