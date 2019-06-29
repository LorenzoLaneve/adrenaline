package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.scene.Node;

public interface Dialog {

    Node createDialogPane(GUIGameWindow window);

    void close();

}
