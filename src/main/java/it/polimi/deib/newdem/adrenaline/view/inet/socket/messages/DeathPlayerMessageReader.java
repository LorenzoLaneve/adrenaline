package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.DeathPlayerEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper;
import java.io.DataInputStream;
import java.io.IOException;

public class DeathPlayerMessageReader implements SocketMessageReader {
    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        return new DeathPlayerEvent(StreamHelper.readPlayerColor(input));
    }
}
