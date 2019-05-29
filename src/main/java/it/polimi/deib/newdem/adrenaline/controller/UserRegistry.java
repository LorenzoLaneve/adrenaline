package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.events.RejectUsernameEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRegistry {

    private List<User> unnamedUsers;

    private HashMap<String, User> userNames;

    private ServerInstance core;

    public UserRegistry(ServerInstance core){
        this.unnamedUsers = new ArrayList<>();
        this.userNames = new HashMap<>();

        this.core = core;
    }

    public void registerUser(User user) {
        core.getLogger().info(String.format("User %s registered to server.", user));

        unnamedUsers.add(user);
        // TODO user.addReceiver(this);

        user.sendEvent(new UpdateUsernameRequest());
    }

    public synchronized void setNameForUser(String name, User user) {
        if (userNames.get(name) != null) {
            core.getLogger().info(String.format("User %s tried to update their name to %s, which is already taken.", user, name));
            user.sendEvent(new RejectUsernameEvent(name));
        } else {
            userNames.put(name, user);
            user.setName(name);
            unnamedUsers.remove(user);

            core.getLogger().info(String.format("User %s successfully changed their name to %s.", user, name));
        }
    }

    public User getUserByName(String name){
        return userNames.get(name);
    }
}
