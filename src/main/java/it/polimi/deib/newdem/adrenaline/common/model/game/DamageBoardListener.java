package it.polimi.deib.newdem.adrenaline.common.model.game;

public interface DamageBoardListener {

    void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer);

}
