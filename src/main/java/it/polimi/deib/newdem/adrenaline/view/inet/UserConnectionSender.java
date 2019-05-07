package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;

public interface UserConnectionSender {


    void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException;


    void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException;


}
