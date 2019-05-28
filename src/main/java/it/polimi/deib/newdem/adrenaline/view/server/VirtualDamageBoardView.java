package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.DamageBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerConvertMarksEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerDamageEvent;

public class VirtualDamageBoardView implements DamageBoardView, DamageBoardListener {

    private Player owner;

    private VirtualGameView gameView;

    public VirtualDamageBoardView(Player owner, VirtualGameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
    }


    @Override
    public void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer) {
        registerDamage(damageAmount, markAmount, dealer.getColor());
    }

    @Override
    public void boardDidConvertMarks(Player dealer) {
        convertMarks(dealer.getColor());
    }


    @Override
    public void registerDamage(int damageAmount, int markAmount, PlayerColor dealer) {
        gameView.sendEvent(new PlayerDamageEvent(owner.getColor(), dealer, damageAmount, markAmount));
    }

    @Override
    public void convertMarks(PlayerColor dealer) {
        gameView.sendEvent(new PlayerConvertMarksEvent(owner.getColor()));
    }

}
