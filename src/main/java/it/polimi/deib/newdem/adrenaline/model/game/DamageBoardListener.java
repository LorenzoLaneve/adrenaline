package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface DamageBoardListener {

    void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer);

    void boardDidConvertMarks(Player dealer);

}
