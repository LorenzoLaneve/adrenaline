package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog that asks the user to choose an action among the ones passed to the constructor.
 * The choice will be notified to the {@code Listener} passed to the constructor.
 */
public class ActionSelectionDialog implements Dialog {

    @FunctionalInterface
    public interface Listener {

        void actionTypeChosen(ActionType actionType);

    }

    private List<ActionType> actionTypes;

    private Listener listener;


    public ActionSelectionDialog(Listener listener, List<ActionType> actionTypes) {
        this.actionTypes = new ArrayList<>(actionTypes);
        this.listener = listener;
    }


    private static String screenNameForAtomicAction(AtomicActionType actionType) {
        switch (actionType) {
            case MOVE4:
                return "Move x4";
            case MOVE3:
                return "Move x3";
            case MOVE2:
                return "Move x2";
            case MOVE1:
                return "Move x1";
            case GRAB:
                return "Pick up";
            case SHOOT:
                return "Use Weapon";
            case RELOAD:
                return "Reload Weapon";
            case USE_POWERUP:
                return "Use Power Up";
        }
        return null;
    }

    private static String screenNameForAction(ActionType action) {
        StringBuilder builder = new StringBuilder();
        for (AtomicActionType atom : action.getAtomicTypes()) {
            builder.append(", ");
            builder.append(screenNameForAtomicAction(atom));
        }
        return builder.toString().substring(2);
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/action-selection.fxml"));

            Pane actionButtons = (Pane) dialogPane.lookup("#actionButtons");
            for (ActionType action : actionTypes) {
                Button button = new Button(screenNameForAction(action));

                button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.actionTypeChosen(action));

                actionButtons.getChildren().add(button);
            }

            Button endTurnButton = (Button) dialogPane.lookup(".end-turn-button");
            endTurnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.actionTypeChosen(null));

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
