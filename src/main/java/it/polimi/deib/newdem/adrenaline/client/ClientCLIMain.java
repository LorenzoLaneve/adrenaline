package it.polimi.deib.newdem.adrenaline.client;

import it.polimi.deib.newdem.adrenaline.view.client.ClientInstance;
import it.polimi.deib.newdem.adrenaline.view.client.cli.CLIViewMaker;

import java.util.logging.Logger;

public class ClientCLIMain {

    /**
     * Starts a new client instance with Command Line Interface.
     */
    public static void main(String[] args) {

        try (ClientInstance cm = new ClientInstance(new CLIViewMaker(Logger.getGlobal(), System.out, System.in))) {
            cm.start();
        } catch (Exception x) {
            Logger.getGlobal().severe("Exception thrown: "+ x.getMessage());
        }

    }

}
