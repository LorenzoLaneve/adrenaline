package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UpdateUsernameEvent;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket.StreamHelper;

import java.io.DataInputStream;
import java.io.IOException;

public class UpdateUsernameMessageReader implements SocketMessageReader {


    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        String username = StreamHelper.readString(input);

        return new UpdateUsernameEvent(username);
    }


}
