package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ExitLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyTimerUpdateEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;

public interface UserConnectionSender {


    void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException;

    void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException;

    void sendExitLobbyEvent(ExitLobbyEvent event) throws ConnectionException;

    void sendLobbyTimerUpdateEvent(LobbyTimerUpdateEvent event) throws ConnectionException;

}
