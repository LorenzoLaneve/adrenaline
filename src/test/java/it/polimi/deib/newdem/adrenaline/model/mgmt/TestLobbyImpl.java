package it.polimi.deib.newdem.adrenaline.model.mgmt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLobbyImpl {

    Lobby lobby;
    User user;
    LobbyListener listener;

    @Before
    public void setUp() throws Exception {
        lobby = new LobbyImpl();
        listener = null;
        user = new User();
    }

    @Test
    public void setListener() {
        lobby.setListener(listener);
    }

    @Test
    public void addUser() {
        lobby.addUser(user);

        assertTrue(lobby.getUsers().contains(user));
    }

    @Test
    public void removeUser() {
        lobby.addUser(user);
        lobby.removeUser(user);

        assertFalse(lobby.getUsers().contains(user));
    }

    @Test
    public void startTimer() {
        //fail();
    }

    @Test
    public void refreshTimer() {
        //fail();
    }

    @Test
    public void abortTimer() {
        //fail();
    }

    @Test
    public void getUsers() {
        lobby.addUser(user);

        assertTrue(lobby.getUsers().contains(user));
    }
}