package it.polimi.deib.newdem.adrenaline.model.items;

public interface Weapon {

    WeaponCard getCard();

    boolean isLoaded();

    void call();

    void reload();
}
