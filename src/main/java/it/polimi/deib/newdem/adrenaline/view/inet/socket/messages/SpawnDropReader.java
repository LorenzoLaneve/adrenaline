package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.SpawnDropEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;

import java.io.DataInputStream;
import java.io.IOException;

import static it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper.readDropType;
import static it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper.readTilePosition;

public class SpawnDropReader implements SocketMessageReader {
    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        return new SpawnDropEvent(
                readDropType(input),
                readDropType(input),
                readDropType(input),
                readTilePosition(input)
                );
    }
}
