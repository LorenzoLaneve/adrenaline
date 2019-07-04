package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameResultsDialog implements Dialog {

    private GameResults results;

    public GameResultsDialog(GameResults results) {
        this.results = results;
    }

    private static String colorToScreenName(PlayerColor color) {
        switch (color) {
            case MAGENTA:
                return "Magenta";
            case CYAN:
                return "Cyan";
            case GRAY:
                return "Gray";
            case YELLOW:
                return "Yellow";
            case GREEN:
                return "Green";
        }
        return null;
    }

    private static Label getNameLabel(Pane diagPane, int i) {
        return (Label) diagPane.lookup(".res-name."+ i);
    }

    private static Label getScoreLabel(Pane diagPane, int i) {
        return (Label) diagPane.lookup(".scr-name."+ i);
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/results-dialog.fxml"));

            int i = 0;
            for (GameResults.PlayerScoreRecord record : results.getRecords()) {
                getNameLabel(dialogPane, i).setText(colorToScreenName(record.getPlayer()));
                getScoreLabel(dialogPane, i).setText(""+ record.getScore());
                i++;
            }

            Button button = (Button) dialogPane.lookup(".terminate-button");
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.exit(0));

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
