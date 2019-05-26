package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper;

import java.io.DataInputStream;
import java.io.IOException;

public class UpdateUsernameReader implements SocketMessageReader {
    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        return new UpdateUsernameEvent(StreamHelper.readString(input));
    }
}
