package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface Weapon {

    WeaponCard getCard();

    boolean isLoaded();

    void discharge();

    void reload();

    Player returnOwner();
}
