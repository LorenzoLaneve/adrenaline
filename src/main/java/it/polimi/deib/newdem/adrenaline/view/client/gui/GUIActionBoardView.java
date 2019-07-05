package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.ActionBoardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GUIActionBoardView implements ActionBoardView {

    private GUIGameWindow window;

    private PlayerColor owner;

    public GUIActionBoardView(GUIGameWindow window, PlayerColor owner) {
        this.window = window;
        this.owner = owner;
    }

    @Override
    public void flipActionBoard() {

        Pane actionBoardPane = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(owner)+" .action-board");
        try {
            actionBoardPane.getStyleClass().add("frenzy");

            Node newGrid = FXMLLoader.load(getClass().getResource("/gui/action-board-frenzy.fxml"));

            actionBoardPane.getChildren().clear();
            actionBoardPane.getChildren().add(newGrid);
        } catch (Exception x) {
            // nothing to do here.
        }


    }

}
