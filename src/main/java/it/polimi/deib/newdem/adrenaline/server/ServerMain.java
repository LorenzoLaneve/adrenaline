package it.polimi.deib.newdem.adrenaline.server;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.InvalidStateException;
import it.polimi.deib.newdem.adrenaline.controller.ServerInstance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    public static void main(String[] args) {
        ServerInstance instance = new ServerInstance(Logger.getGlobal(), Config.fromFile("./config.json"));

        instance.init();

        try {
            instance.start();
        } catch (InvalidStateException x) {
            instance.getLogger().log(Level.SEVERE, "Could not start server.");
        }

    }

}
