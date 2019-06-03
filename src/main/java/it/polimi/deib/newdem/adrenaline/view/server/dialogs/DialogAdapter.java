package it.polimi.deib.newdem.adrenaline.view.server.dialogs;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

public interface DialogAdapter<T> {

    void sendDialogRequest(Dialog<T> dialog, User user, List<T> admittedChoices);

}
