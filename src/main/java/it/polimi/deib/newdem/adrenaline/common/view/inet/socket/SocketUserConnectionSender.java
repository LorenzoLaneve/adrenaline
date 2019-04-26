package it.polimi.deib.newdem.adrenaline.common.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.common.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.common.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.common.view.inet.events.UpdateUsernameEvent;

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



        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }


}
