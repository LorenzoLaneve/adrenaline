package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public class PlayerImpl implements Player {

    private PlayerInventory inventory;

    private ActionBoard actionBoard;

    private DamageBoard damageBoard;


    @Override
    public Game getGame() {
        //TODO
        return null;
    }

    @Override
    public PlayerInventory getInventory() {
        //TODO
        return null;
    }

    @Override
    public String getName() {
        //TODO
        return null;
    }

    @Override
    public PlayerColor getColor() {
        //TODO
        return null;
    }

    @Override
    public List<ActionFactory> getMoves() {
        //TODO
        return null;
    }

    @Override
    public int getDeaths() {
        //TODO
        return 0;
    }

    @Override
    public int getTotalDamage() {
        //TODO
        return 0;
    }

    @Override
    public Player getDamager(int cell) {
        //TODO
        return null;
    }

    @Override
    public int getDamageFromPlayer(Player player) {
        //TODO
        return 0;
    }

    @Override
    public int getMarksFromPlayer(Player player) {
        //TODO
        return 0;
    }

    @Override
    public boolean isDead() {
        //TODO
        return false;
    }
}
