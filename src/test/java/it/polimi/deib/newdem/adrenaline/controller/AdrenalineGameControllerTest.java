package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.MockPowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdrenalineGameControllerTest {

    @Test
    public void testSetupGame() throws Exception {
        GameController gameController = new AdrenalineGameController(null);

        gameController.setupGame(Arrays.asList(
                new User()
        ));
    }
}