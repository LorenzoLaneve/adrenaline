package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestAdrenalineGameController {

    private AdrenalineGameController gameController;

    private User user;

    private class MockLobbyController implements LobbyController{

        @Override
        public Lobby getLobby() {
            return null;
        }

        @Override
        public void addUser(User user) {

        }

        @Override
        public void removeUser(User user) {

        }

        @Override
        public TimerListener getTimerListener() {
            return null;
        }

        @Override
        public boolean acceptsNewUsers() {
            return false;
        }

        @Override
        public Config getConfig() {
            return Config.getDefaultConfig();
        }

        @Override
        public void startGame() {

        }

        @Override
        public void endGame() {

        }
    }

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        LobbyController lobbyController = new MockLobbyController();
        gameController = new AdrenalineGameController(lobbyController);

        user = new User();

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        List<User> users = new ArrayList<>();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user);

        gameController.setupGame(users);

    }


    @Test
    public void runGame() {
    }

    @Test
    public void userDidDisconnect() {
        gameController.userDidDisconnect(user);
    }

    @Test
    public void userDidReconnect() {
        gameController.userDidDisconnect(user);
        gameController.userDidReconnect(user);
    }
}