package it.polimi.deib.newdem.adrenaline.client;


import it.polimi.deib.newdem.adrenaline.view.client.ClientInstance;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIViewMaker;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;


public class ClientGUIMain extends Application {

    private static Thread clientThread;

    @Override
    public void start(Stage primaryStage) {
        clientThread = new Thread(this::setupClient);
        clientThread.start();
    }

    /**
     * Starts the ClientInstance object, this should be called in a separate thread and NOT in the JavaFX thread.
     */
    private void setupClient() {
        try (ClientInstance client = new ClientInstance(new GUIViewMaker(this))) {
            client.start();
        } catch (Exception x) {
            Logger.getGlobal().severe("Exception thrown: "+ x.getMessage());
        }
    }


    /**
     * Starts a new client instance with Graphics User Interface.
     */
    public static void main(String[] args) {
        launch(args);

        if (clientThread != null && clientThread.isAlive()) {
            clientThread.interrupt();
        }
    }

}
