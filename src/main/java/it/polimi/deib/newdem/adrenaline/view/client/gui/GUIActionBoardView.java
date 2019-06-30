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

        Pane actionBoardPane = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(owner)+" .action-board");
        try {
            actionBoardPane.getStyleClass().add("frenzy");

            //Node oldGrid = actionBoardPane.getChildren().get(0);
            Node newGrid = FXMLLoader.load(getClass().getResource("/gui/action-board-frenzy.fxml"));

            actionBoardPane.getChildren().clear();
            actionBoardPane.getChildren().add(newGrid);

            // FIXME for concurrency reasons the event listeners may be applied before the flip.
        } catch (Exception x) {
            // nothing to do here.
        }


    }

}
