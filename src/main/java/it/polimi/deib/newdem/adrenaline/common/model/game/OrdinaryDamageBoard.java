package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdinaryDamageBoard extends DamageBoardImpl {

    public OrdinaryDamageBoard(Player player) {
        super(player);
        score = new ArrayList<>(Arrays.asList(8,6,4,2,1,1));
    }

    @Override
    public List<ActionFactory> getAdditionalActions(int totalDamage) {
        // TODO implement
        return null;
    }

    @Override
    protected boolean shouldAssignFirstBlood() {
        return true;
    }
}
