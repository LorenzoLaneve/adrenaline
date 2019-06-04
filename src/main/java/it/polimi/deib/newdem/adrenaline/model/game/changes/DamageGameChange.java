package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.DamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.MapListener;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;
import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.MAX_LIFE;
import static java.lang.Math.min;

public class DamageGameChange implements GameChange {

    private Player attacker;
    private Player attacked;
    private int desiredDmg;
    private int desiredMrk;
    private int actualDmg;
    private int previousMrk;
    private boolean didDie;


    public DamageGameChange(Player attacker, Player attacked, int dmgAmt, int mrkAmt){
        this.attacked = attacked;
        this.attacker = attacker;
        this.desiredDmg = dmgAmt;
        this.desiredMrk = mrkAmt;
        didDie = false;
    }

    @Override
    public void update(Game game) {
        // apply
        DamageBoard dmgb = attacked.getDamageBoard();

        // if dmg > 0, convert mark
        previousMrk = dmgb.getTotalMarksFromPlayer(attacker);

        if(desiredDmg > 0) {
            dmgb.setMarksFromPlayer(0,attacker);
        }

        // apply as much of new damage as possible
        actualDmg = min(desiredDmg, MAX_LIFE - dmgb.getTotalDamage());
        int damageLeft = actualDmg;
        while (damageLeft > 0) {
            dmgb.setDamage(
                    dmgb.getTotalDamage(),
                    attacker
            );
            damageLeft--;
        }

        // apply new mark
        dmgb.setMarksFromPlayer(desiredMrk, attacker);

        // declare death if applicable
        if(dmgb.getTotalDamage() >= DEATH_SHOT_INDEX) {
            attacked.reportDeath(true);
            didDie = true;
        }
    }

    @Override
    public void revert(Game game) {
        //TODO
        DamageBoard dmgb = attacked.getDamageBoard();

        // rez
        if(didDie) {
            attacked.reportDeath(false);
            MapListener mapListener = game.getMap().getListener();
            if(null != mapListener) {
                mapListener.playerDidResurrect(attacked);
            }
        }

        // revert marks
        dmgb.setMarksFromPlayer(previousMrk, attacker);

        // revert damage
        int dmgCursor = actualDmg;
        while (dmgCursor > 0) {
            int dmgIndex = dmgb.getTotalDamage() - 1;

            // check for legal state gracefully
            if(dmgIndex < 0 || dmgIndex > MAX_LIFE) throw new IndexOutOfBoundsException();
            if(!attacker.equals(dmgb.getDamager(dmgIndex))) throw new IllegalStateException();

            dmgb.setDamage(dmgb.getTotalDamage() - 1, null);
            dmgCursor--;
        }
    }
}
