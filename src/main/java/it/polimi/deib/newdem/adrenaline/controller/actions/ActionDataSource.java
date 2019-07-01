package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface ActionDataSource {

    Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException;

    WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException;

    PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException;

    Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException;

    Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException;

    PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException;

}
