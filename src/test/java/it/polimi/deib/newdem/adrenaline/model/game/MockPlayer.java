package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;

import java.util.List;

public class MockPlayer extends PlayerImpl {

    private PlayerColor color;
    private String name;
    private DamageBoard damageBoard;
    private int deathCount;

    public MockPlayer() {
        super(PlayerColor.YELLOW, new MockGame());
        color = PlayerColor.YELLOW;
        name = "Alice";
        deathCount = 0;
        this.damageBoard = null;
    }

    public MockPlayer(PlayerColor color) {
        super(PlayerColor.YELLOW, new MockGame());
        this.color = color;
        deathCount = 0;
        this.damageBoard = null;
    }

    public MockPlayer(PlayerColor color, String name) {
        super(color, new MockGame());
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
}
