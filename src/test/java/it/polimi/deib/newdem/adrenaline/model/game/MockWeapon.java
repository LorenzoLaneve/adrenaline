package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class MockWeapon implements Weapon {

    private String name;
    private boolean ready;
    private WeaponCard card;
    private Player player;

    public MockWeapon(WeaponCard card, boolean ready) {
        this.ready = ready;
        this.card = card;
        this.name =  ((MockWeaponCard) card).getName();
    }

    public MockWeapon(String name, boolean ready) {
        this.name = name;
        this.ready = ready;
        this.card = new MockWeaponCard(name);
    }

    public MockWeapon(String name) {
        this.name = name;
        this.ready = true;
        this.card = new MockWeaponCard(name);
    }

    @Override
    public int hashCode() {
        return 4*16+2;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MockWeapon)) return false;
        return name.equals(((MockWeapon) obj).name);
    }

    @Override
    public WeaponCard getCard() {
        if(null != this.card) return this.card;
        return new MockWeaponCard(name);
    }

    @Override
    public boolean isLoaded() {
        return ready;
    }

    @Override
    public void discharge() {

    }

    @Override
    public void reload() {

    }

    @Override
    public Player getOwner() {
        return null;
    }

}
