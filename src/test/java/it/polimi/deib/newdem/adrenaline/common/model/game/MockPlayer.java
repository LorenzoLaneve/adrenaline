package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public class MockPlayer implements Player{

    private PlayerColor color;
    private String name;

    public MockPlayer(PlayerColor color) {
        this.color = color;
    }

    public MockPlayer(PlayerColor color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    @Override
    public PlayerColor getColor() {
        return color;
    }

    @Override
    public List<ActionFactory> getMoves() {
        return null;
    }

    @Override
    public int getDeaths() {
        return 0;
    }

    @Override
    public int getTotalDamage() {
        return 0;
    }

    @Override
    public Player getDamager(int cell) {
        return null;
    }

    @Override
    public int getDamageFromPlayer(Player player) {
        return 0;
    }

    @Override
    public int getMarksFromPlayer(Player player) {
        return 0;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
