package it.polimi.deib.newdem.adrenaline.common.mechs.game;

public abstract class DamageBoardImpl implements  DamageBoard {

    Player player;

    @Override
    public Player getPlayer() {
        return player;
        // TODO implement
    }

    public DamageBoardImpl(Player player) {
        this.player = player;
        // TODO implement
    }
}
