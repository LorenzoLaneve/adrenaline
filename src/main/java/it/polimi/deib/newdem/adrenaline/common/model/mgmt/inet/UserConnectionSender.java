package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UpdateUsernameEvent;

public interface UserConnectionSender {


    void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException;


    void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException;


}
