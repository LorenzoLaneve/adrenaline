package it.polimi.deib.newdem.adrenaline.common.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.common.view.inet.events.UpdateUsernameEvent;
import it.polimi.deib.newdem.adrenaline.common.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.StreamHelper;

import java.io.DataInputStream;
import java.io.IOException;

public class UpdateUsernameMessageReader implements SocketMessageReader {


    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        String username = StreamHelper.readString(input);

        return new UpdateUsernameEvent(username);
    }


}
