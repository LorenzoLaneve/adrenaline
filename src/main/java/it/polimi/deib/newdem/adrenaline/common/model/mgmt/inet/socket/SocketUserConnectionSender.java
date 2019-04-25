package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UpdateUsernameEvent;

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
