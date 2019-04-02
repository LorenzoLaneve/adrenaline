package it.polimi.deib.newdem.adrenaline.common.mechs.game;

public abstract class DamageBoardImpl implements  DamageBoard {

    private Player player;

    public DamageBoardImpl(Player player) {
        this.player = player;
        // TODO implement
    }

    @Override
    public Player getPlayer() {
        return player;
        // TODO implement
    }
}
