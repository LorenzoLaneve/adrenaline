package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.MapListener;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.DEATH_SHOT_INDEX;
import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.MAX_LIFE;
import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.MAX_MARKS;
import static java.lang.Math.min;
import static java.lang.Math.multiplyExact;

public class DamageGameChange implements GameChange {

    private Player attacker;
    private Player victim;
    private int desiredDmg;
    private int desiredMrk;
    private int actualDmg;
    private int previousMrk;
    private boolean didDie;


    public DamageGameChange(Player attacker, Player attacked, int dmgAmt, int mrkAmt){
        this.victim = attacked;
        this.attacker = attacker;
        this.desiredDmg = dmgAmt;
        this.desiredMrk = mrkAmt;
        didDie = false;
    }

    @Override
    public void update(Game game) {
        DamageBoard victimBoard = victim.getDamageBoard();

        try {
            for (int i = desiredDmg; i > 0; i--) {
                victimBoard.appendDamage(attacker);
                // ^ implies resolution of previous marks if applicable
                // note that for desiredDmg = 0; it's never called
                // and marks are not reset
            }
        }
        catch (DamageTrackFullException e) {
            // do nothing
        }

        victimBoard.setMarksFromPlayer(
                min(victimBoard.getTotalMarksFromPlayer(attacker) + desiredMrk, MAX_MARKS),
                attacker
        );

        if(victimBoard.getTotalDamage() > DEATH_SHOT_INDEX) {
            victim.reportDeath(true);
            didDie = true;
        }

        /*
        int damageToDeal = desiredDmg;
        DamageBoard victimBoard = victim.getDamageBoard();

        previousMrk = victim.getMarksFromPlayer(attacker);

        if(damageToDeal > 0) {
            if(desiredMrk > 0) {
                damageToDeal += victimBoard.getTotalMarksFromPlayer(attacker);
                victimBoard.setMarksFromPlayer(0, attacker);
                victimBoard.getListener().boardDidConvertMarks(attacker);
            }

            try {
                while (damageToDeal > 0) {
                    victimBoard.appendDamage(attacker);
                    damageToDeal--;
                }
            }
            catch (DamageTrackFullException e) {
                // that's ok
            }
            finally {
                actualDmg = desiredDmg - damageToDeal;
            }

            int newMarks = min(desiredMrk + victimBoard.getTotalMarksFromPlayer(attacker), DamageBoardImpl.MAX_MARKS);
            victimBoard.setMarksFromPlayer(newMarks, attacker);

            if(victimBoard.getTotalDamage() > DEATH_SHOT_INDEX) {
                victim.reportDeath(true);
                didDie = true;
            }
        }
        else{
            // just marks
            int newMarks = min(desiredMrk + victimBoard.getTotalMarksFromPlayer(attacker), DamageBoardImpl.MAX_MARKS);
            victimBoard.setMarksFromPlayer(newMarks, attacker);
            actualDmg = 0;
        }
        */
    }

    @Override
    public void revert(Game game) {
        //TODO
        DamageBoard dmgb = victim.getDamageBoard();

        // rez
        if(didDie) {
            victim.reportDeath(false);
            MapListener mapListener = game.getMap().getListener();
            if(null != mapListener) {
                mapListener.playerDidResurrect(victim);
            }
        }

        // revert marks
        dmgb.setMarksFromPlayer(previousMrk, attacker);

        // revert damage
        int dmgCursor = actualDmg;
        try {
            while (dmgCursor > 0) {
                int dmgIndex = dmgb.getTotalDamage() - 1;

                // check for legal state gracefully
                if (dmgIndex < 0 || dmgIndex > MAX_LIFE) throw new IndexOutOfBoundsException();
                if (!attacker.equals(dmgb.getDamager(dmgIndex))) throw new IllegalStateException();

                Player removed = dmgb.popDamage();
                if(!removed.equals(attacker)) throw new IllegalStateException();
                dmgCursor--;
            }
        }
        catch (DamageTrackEmptyException e) {
            // do what now?
            // this should never happen
            throw new IllegalStateException(e);
        }
    }
}
