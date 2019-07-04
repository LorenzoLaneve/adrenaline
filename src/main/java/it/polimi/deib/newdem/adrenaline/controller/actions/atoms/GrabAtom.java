package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;


public class GrabAtom extends AtomBase {

    public GrabAtom(AtomsContainer parent) {
        super(parent, new EntryPointFactory(EntryPointType.GRAB));
    }

}

