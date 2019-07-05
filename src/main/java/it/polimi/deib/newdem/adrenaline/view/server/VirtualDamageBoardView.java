package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.DamageBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

/**
 * A virtual view is a view object that acts as an adapter between model/controller and views,
 * translating the model objects into plain data objects usable by views.
 * This way the view is completely separated from the model and we do not need to clone/reflect
 * model objects into the client.
 *
 * Note: the VirtualGameView is also used to give information about the in-game users to the other
 * virtual views.
 */
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
    public void boardDidPopDamage() {
        popDamage();
    }

    @Override
    public void boardDidSwitchToFrenzy() {
        goFrenzy();
    }

    @Override
    public void boardDidClear() {
        clearBoard();
    }


    @Override
    public void registerDamage(int damageAmount, int markAmount, PlayerColor dealer) {
        gameView.sendEvent(new PlayerDamageEvent(owner.getColor(), dealer, damageAmount, markAmount));
    }


    @Override
    public void popDamage() {
        gameView.sendEvent(new PlayerPopDamageEvent(owner.getColor()));
    }

    @Override
    public void goFrenzy() {
        gameView.sendEvent(new DamageBoardFlipEvent(owner.getColor()));
    }

    @Override
    public void clearBoard() {
        gameView.sendEvent(new DamageBoardClearEvent(owner.getColor()));
    }

}
