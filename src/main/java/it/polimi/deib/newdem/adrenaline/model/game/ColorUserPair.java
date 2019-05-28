package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class ColorUserPair {
    private final User user;
    private final PlayerColor color;

    public User getUser() {
        return user;
    }

    public PlayerColor getColor() {
        return color;
    }

    public ColorUserPair(PlayerColor color, User user) {
        this.user = user;
        this.color = color;
    }
}
