package it.polimi.deib.newdem.adrenaline.server;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.InvalidStateException;
import it.polimi.deib.newdem.adrenaline.controller.ServerInstance;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    /**
     * Starts a new ServerInstance.
     * This methods will look for config.json in the artifact directory.
     * If the file does not exist, or the JSON in it is malformed, then an error will be reported and the program will end.
     */
    public static void main(String[] args) {
        Config serverConf;

        File configFile = new File("config.json");
        if (!configFile.exists()) {
            Logger.getGlobal().severe("Could not load config: no such file or directory in "+ configFile.getAbsolutePath());
            return;
        }

        try {
            serverConf = Config.fromFile("config.json");
        } catch (Exception x) {
            Logger.getGlobal().severe("Could not load config: "+ x);
            return;
        }
        Logger.getGlobal().info("Config file successfully loaded from "+ configFile.getAbsolutePath());


        ServerInstance instance = new ServerInstance(Logger.getGlobal(), serverConf);
        instance.init();

        try {
            instance.start();
        } catch (InvalidStateException x) {
            instance.getLogger().log(Level.SEVERE, "Could not start server.");
        }

    }

}
