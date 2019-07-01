package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class LobbyRegistryTest {

    @Test
    public void getLobbyByUserTest() {
        Config c;
        try {
            c = Config.fromFile(FileUtils.getAbsoluteDecodedFilePath("configtest.json", this.getClass()));
        } catch (InvalidConfigException e) {
            fail();
            return;
        }

        ServerInstance instance = new ServerInstance(Logger.getGlobal(), c);
        instance.init();

        LobbyRegistry registry = instance.getLobbyRegistry();

        User user1 = new User();
        user1.setName("Pippo");

        assertNull(registry.getLobbyByUser(user1));

        registry.assignLobby(user1);
        assertNotNull(registry.getLobbyByUser(user1));

        registry.removeUser(user1);
        assertNull(registry.getLobbyByUser(user1));
    }

}
