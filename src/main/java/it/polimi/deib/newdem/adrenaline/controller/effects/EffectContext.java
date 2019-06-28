package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface EffectContext {

    void applyGameChange(GameChange gameChange);

    void revertGameChange(GameChange gameChange);


    Player getActor();


    Player getAttacker();

    Player getVictim();


    Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException;

    Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException;

    Integer chooseFragment(List<Integer> choices) throws UndoException;

    PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException;


    void damageDealtTrigger(Player attacker, Player victim);

    void damageTakenTrigger(Player attacker, Player victim);

}
