package it.polimi.deib.newdem.adrenaline.common.mechs.items;

public interface Weapon {

    WeaponCard getCard();

    boolean isLoaded();

    void call();

    void reload();
}
