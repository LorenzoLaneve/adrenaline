package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class WeaponImpl implements Weapon {

    private WeaponCard card;
    private Player player;
    private boolean isLoaded;

    public WeaponImpl(WeaponCard card, Player player) {
        this.card = card;
        this.player = player;
    }

    @Override
    public WeaponCard getCard() {
        return card;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void discharge() {
        if(isLoaded){
            isLoaded = false;
            player.getListener().playerDidUnloadWeaponCard(player, card);
        }
    }

    @Override
    public void reload() {
        if(!isLoaded){
            isLoaded = true;
            player.getListener().playerDidReloadWeaponCard(player, card);
        }
    }

    @Override
    public Player returnOwner() {
        return player;
    }


}
