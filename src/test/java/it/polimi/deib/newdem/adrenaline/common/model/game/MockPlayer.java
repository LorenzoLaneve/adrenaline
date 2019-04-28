package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public class MockPlayer implements Player {

    private PlayerColor color;
    private String name;
    private DamageBoard damageBoard;
    private int deathCount;

    public MockPlayer() {
        color = PlayerColor.YELLOW;
        name = "Alice";
        deathCount = 0;
        this.damageBoard = null;
    }

    public MockPlayer(PlayerColor color) {
        this.color = color;
        deathCount = 0;
        this.damageBoard = null;
    }

    public MockPlayer(PlayerColor color, String name) {
        this.color = color;
        this.name = name;
        this.damageBoard = null;
        deathCount = 0;
    }

    public void registerDamageBoard(DamageBoard d) {
        this.damageBoard = d;
    }

    public void die() {
        deathCount += 1;
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
        return deathCount;
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

    @Override
    public void takeDamage(int dmgAmount, Player attacker) {

    }

    @Override
    public void takeMark(int markAmount, Player attacker) {

    }
}
