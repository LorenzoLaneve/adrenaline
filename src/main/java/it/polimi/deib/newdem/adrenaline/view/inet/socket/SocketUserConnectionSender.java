package it.polimi.deib.newdem.adrenaline.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ExitLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyTimerUpdateEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;

import java.io.DataOutputStream;
import java.io.IOException;

public class SocketUserConnectionSender implements UserConnectionSender {

    private DataOutputStream output;


    public SocketUserConnectionSender(DataOutputStream output) {
        this.output = output;
    }



    @Override
    public void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.UPDATE_USER_NAME);

            StreamHelper.writeString(output, event.getNewUsername());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }


    @Override
    public void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.ENTER_LOBBY);

            // TODO implement here

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendExitLobbyEvent(ExitLobbyEvent event) throws ConnectionException {
        // TODO implement here
    }

    @Override
    public void sendLobbyTimerUpdateEvent(LobbyTimerUpdateEvent event) throws ConnectionException {
        // TODO implement here
    }


}
