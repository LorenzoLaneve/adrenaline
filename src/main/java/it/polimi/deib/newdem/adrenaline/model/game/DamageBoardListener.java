package it.polimi.deib.newdem.adrenaline.model.game;

public interface DamageBoardListener {

    void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer);

    void boardDidConvertMarks(Player dealer);

}
