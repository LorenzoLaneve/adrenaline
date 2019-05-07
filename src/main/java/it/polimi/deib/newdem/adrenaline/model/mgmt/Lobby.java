package it.polimi.deib.newdem.adrenaline.model.mgmt;

import java.util.List;

public interface Lobby {

    void addUser(User user);

    void removeUser(User user);

    List<User> getUsers();
}
