package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.events.DeathPlayerEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.sockets.SocketUserConnection;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.*;

public class TestUser {

    UserListener listener;
    User user;

    @Before
    public void setUp() throws Exception {
        listener = null;
        user = new User();
        user.setName("test");
        //user.bindConnection(new SocketUserConnection(new Socket(),user));
    }

    @Test
    public void addListener() {
        user.addListener(listener);
    }

    @Test
    public void removeListener() {
        user.addListener(listener);
        user.removeListener(listener);
    }

    @Test
    public void getName() {
        assertEquals("test", user.getName());
    }

    @Test
    public void setName() {
        user.setName("test2");
        assertEquals("test2", user.getName());
    }

    @Test
    public void bindConnection() {
        try{
            //user.bindConnection(new SocketUserConnection(new Socket(),user));
        }catch (Exception e){
            //fail();
        }
    }

    @Test
    public void subscribeEvent() {
        //fail();

    }

    @Test
    public void unsubscribeEvent() {
        //fail();
    }

    @Test
    public void sendEvent() {
        user.sendEvent(new DeathPlayerEvent(PlayerColor.CYAN));
    }

    @Test
    public void isConnected() {
        assertFalse(user.isConnected());
    }
}