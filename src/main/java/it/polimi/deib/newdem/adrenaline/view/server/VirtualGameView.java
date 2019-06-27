package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.GameListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.Collection;
import java.util.EnumMap;

public class VirtualGameView implements GameView, GameListener {

    private EnumMap<PlayerColor, User> users;

    public VirtualGameView() {
        this.users = new EnumMap<>(PlayerColor.class);
    }

    public void sendEvent(UserEvent event) {
        for (User user : users.values()) {
            user.sendEvent(event);
        }
    }


    public Collection<PlayerColor> getPlayers() {
        return users.keySet();
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public User getUserFromColor(PlayerColor color) {
        return this.users.get(color);
    }


    @Override
    public void gameDidInit(Game game, GameData gameData) {
        setGameData(gameData);
    }

    @Override
    public void gameWillEnd(Game game) {
        sendEvent(new GameEndEvent());
        // FIXME additional info on game over?
    }

    @Override
    public void userDidEnterGame(User user, Player player) {
        this.users.put(player.getColor(), user);
    }

    @Override
    public void userDidExitGame(User user, Player player) {
        this.users.remove(player.getColor());
    }



    @Override
    public void setGameData(GameData data) {
        sendEvent(new GameDataEvent(data));
    }

    @Override
    public void disablePlayer(PlayerColor color) {
        sendEvent(new PlayerDisconnectEvent(color));
    }

    @Override
    public void enablePlayer(PlayerColor color) {
        sendEvent(new PlayerReconnectEvent(color));
    }

}
