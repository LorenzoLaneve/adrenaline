package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;
import java.util.Map;

import static it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl.MAX_LIFE;
import static java.lang.Math.min;

public class LegacyDamageBoardAdapter implements DamageBoard {

    private DamageBoard innerDmgb;
    private DamageBoardListener listener;

    /**
     * Adapter used inorder not to modify previous tests with slightly different damageboard implementation.
     * @param innerDmgb the damage in the damageboard
     */

    public LegacyDamageBoardAdapter(DamageBoard innerDmgb) {
        this.innerDmgb = innerDmgb;
    }

    /**
     * Register damage from another player.
     * @param dmgAmount amount of damage taken. Not negative, not zero.
     * @param attacker player that dealt the damage. Not null.
     */
    public void takeDamage(int dmgAmount, Player attacker) {
        if(null == attacker || dmgAmount < 0) {
            throw new IllegalArgumentException();
        }
        while(innerDmgb.getTotalDamage() <= MAX_LIFE && dmgAmount > 0) {
            // damages.add(attacker);
            try {
                innerDmgb.appendDamage(attacker, true);
            }
            catch (DamageTrackFullException e) {
                // do nothing
            }
            dmgAmount--;
        }
    }

    /**
     * Register mark(s) from another player.
     * @param markAmount amount of marks taken
     * @param attacker player that dealt the marks
     */
    public void takeMark(int markAmount, Player attacker) {
        if(null == attacker) {
            throw new IllegalArgumentException();
        }
        if(markAmount < 0) {
            throw new IndexOutOfBoundsException();
        }

        innerDmgb.setMarksFromPlayer(
                min(
                        innerDmgb.getTotalMarksFromPlayer(attacker) + markAmount,
                        3
                ),
                attacker
        );
    }

    public boolean shouldAssignFirstBlood() {
        return innerDmgb.shouldAssignFirstBlood();
    }

    public List<ActionFactory> getAdditionalActions() {
        return innerDmgb.getAdditionalActions();
    }

    @Override
    public Player getPlayer() {
        return innerDmgb.getPlayer();
    }

    @Override
    public int getScoreForPlayer(Player player) {
        return innerDmgb.getScoreForPlayer(player);
    }

    @Override
    public Player getDamager(int index) {
        return innerDmgb.getDamager(index);
    }

    @Override
    public int getTotalDamageFromPlayer(Player player) {
        return innerDmgb.getTotalDamageFromPlayer(player);
    }

    @Override
    public int getTotalMarksFromPlayer(Player player) {
        return innerDmgb.getTotalMarksFromPlayer(player);
    }

    @Override
    public Map<Player, Integer> getMarksMap() {
        return innerDmgb.getMarksMap();
    }

    @Override
    public int getTotalDamage() {
        return innerDmgb.getTotalDamage();
    }

    @Override
    public void setMarksFromPlayer(int totalMarks, Player player) {
        innerDmgb.setMarksFromPlayer(totalMarks, player);
    }

    @Override
    public void appendDamage(Player player, boolean canRealizeMarks) throws DamageTrackFullException {
        innerDmgb.appendDamage(player, true);
    }

    @Override
    public Player popDamage() throws DamageTrackEmptyException {
        return innerDmgb.popDamage();
    }

    @Override
    public boolean isFrenzy() {
        return innerDmgb.isFrenzy();
    }

    @Override
    public void setListener(DamageBoardListener listener) {
        innerDmgb.setListener(listener);
        this.listener = listener;
    }

    @Override
    public DamageBoardListener getListener() {
        return innerDmgb.getListener();
    }

    @Override
    public void renewDamageBoard() {
        innerDmgb.renewDamageBoard();
    }
}
