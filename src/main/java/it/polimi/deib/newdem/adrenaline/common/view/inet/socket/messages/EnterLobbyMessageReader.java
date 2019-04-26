package it.polimi.deib.newdem.adrenaline.common.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.common.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.InvalidMessageException;

import java.io.DataInputStream;
import java.io.IOException;

public class EnterLobbyMessageReader implements SocketMessageReader {


    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        // TODO implement here
        return null;
    }


}
