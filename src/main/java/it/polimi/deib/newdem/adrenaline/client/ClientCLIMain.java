package it.polimi.deib.newdem.adrenaline.client;

import it.polimi.deib.newdem.adrenaline.view.client.ClientInstance;
import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;
import it.polimi.deib.newdem.adrenaline.view.client.cli.CLIReader;
import it.polimi.deib.newdem.adrenaline.view.client.cli.CLIViewMaker;

import java.util.logging.Logger;

public class ClientCLIMain {

    /**
     * Starts a new client instance with Command Line Interface.
     */
    public static void main(String[] args) {
        CLIReader in = new CLIReader(System.in);
        in.start();

        try (ClientInstance cm = new ClientInstance(new CLIViewMaker(Logger.getGlobal(), System.out, in))) {
            cm.start();
        } catch (ClosedException x) {
            // that's ok
        } catch (Exception x) {
            Logger.getGlobal().severe("Exception thrown: "+ x.getMessage());
        }

        in.close();
    }

}
