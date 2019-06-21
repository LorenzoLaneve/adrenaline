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

    private void setupClient() {
        try (ClientInstance client = new ClientInstance(new GUIViewMaker(this))) {
            client.start();
        } catch (Exception x) {
            Logger.getGlobal().severe("Exception thrown: "+ x.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);

        if (clientThread != null && clientThread.isAlive()) {
            clientThread.interrupt();
        }
    }

}
