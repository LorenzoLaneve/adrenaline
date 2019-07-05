package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * A simple popup window notifying the user that the server connection is lost.
 * Also a button that will close the application is presented.
 */
public class DisconnectionDialog implements Dialog {

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/disconnection.fxml"));

            Button terminateButton = (Button) dialogPane.lookup(".terminate-button");
            terminateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.exit(0);
            });

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
