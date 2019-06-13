package it.polimi.deib.newdem.adrenaline.model.items;

public class WeaponImpl implements Weapon {

    private WeaponCard card;

    public WeaponImpl(WeaponCard card) {
        this.card = card;
        // TODO implement
    }

    @Override
    public WeaponCard getCard() {
        return card;
    }

    @Override
    public boolean isLoaded() {
        // TODO implement
        return false;
    }

    @Override
    public void call() {
        // TODO implement
    }

    @Override
    public void reload() {
        // TODO implement
    }
}
