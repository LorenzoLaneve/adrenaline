package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.GameListener;
import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.Collection;
import java.util.EnumMap;

/**
 * A virtual view is a view object that acts as an adapter between model/controller and views,
 * translating the model objects into plain data objects usable by views.
 * This way the view is completely separated from the model and we do not need to clone/reflect
 * model objects into the client.
 *
 * Note: the VirtualGameView is also used to give information about the in-game users to the other
 * virtual views.
 */
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
    public void gameWillEnd(Game game, GameResults gameResults) {
        endGame(gameResults);
    }

    @Override
    public void userDidEnterGame(User user, Player player) {
        this.users.put(player.getColor(), user);

        enablePlayer(player.getColor());
    }

    @Override
    public void userDidExitGame(User user, Player player) {
        disablePlayer(player.getColor());
    }

    @Override
    public void playerRestoredMatchData(Game game, Player player) {
        User reconnectingUser = game.getUserByPlayer(player);

        this.users.put(player.getColor(), reconnectingUser);

        reconnectingUser.sendEvent(new GameStartEvent());

        GameData gameData = game.generateGameData();
        reconnectingUser.sendEvent(new GameDataEvent(gameData));

        MapData mapData = game.getMap().generateMapData();
        reconnectingUser.sendEvent(new MapDataEvent(mapData));

        KillTrackData killTrackData = game.generateKillTrackData();
        reconnectingUser.sendEvent(new KillTrackDataEvent(killTrackData));


        for (Player otherPlayer : game.getPlayers()){
            reconnectingUser.sendEvent(new PlayerDataEvent(otherPlayer.generatePlayerData()));
        }

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

    @Override
    public void endGame(GameResults results) {
        sendEvent(new GameEndEvent(results));
    }

}
