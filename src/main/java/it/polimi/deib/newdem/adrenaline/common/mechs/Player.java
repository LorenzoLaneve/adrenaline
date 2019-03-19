package it.polimi.deib.newdem.adrenaline.common.mechs;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.PowerUpSet;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.WeaponSet;

import java.util.List;

public interface Player {

    String getName();

    PlayerColor getColor();

    ActionBoard getActionBoard();

    DamageBoard getDamageBoard();

    AmmoSet getAmmos();
    /*
    At the time of writing, AmmoSet hasn't been intrduced
    not even as a stub

    TODO validate
     */

    List<ActionFactory> getMoves();

    WeaponSet getReadyWeapons();
    /*
    At the time of writing, WeaponSet hasn't been intrduced
    not even as a stub

    TODO validate
     */

    WeaponSet getUnloadedWeapons();
    /*
    At the time of writing, AmmoSet hasn't been intrduced
    not even as a stub

    TODO validate
     */

    PowerUpSet getPowerUp();
    /*
    At the time of writing, PowerUpSet hasn't been intrduced
    not even as a stub

    TODO validate
     */

    boolean isDead();
}
