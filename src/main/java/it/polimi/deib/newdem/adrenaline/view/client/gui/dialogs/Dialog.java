package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.scene.Node;

/**
 * Interface defining a Dialog that can be shown as overlay in the game window.
 */
public interface Dialog {

    /**
     * Creates a new JavaFX Node that will be shown in the overlay when opening this dialog.
     */
    Node createDialogPane(GUIGameWindow window);

}
