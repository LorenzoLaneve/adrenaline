package it.polimi.deib.newdem.adrenaline.server;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.InvalidStateException;
import it.polimi.deib.newdem.adrenaline.controller.ServerInstance;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    public static void main(String[] args) {
        Config serverConf;

        File file =  new File("config.json");
        System.out.println(file.getAbsolutePath());

        try {
            serverConf = Config.fromFile("config.json");
        } catch (Exception x) {
            Logger.getGlobal().severe("Could not load config: "+ x);
            return;
        }

        ServerInstance instance = new ServerInstance(Logger.getGlobal(), serverConf);

        instance.init();

        try {
            instance.start();
        } catch (InvalidStateException x) {
            instance.getLogger().log(Level.SEVERE, "Could not start server.");
        }

    }

}
