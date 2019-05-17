package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RoundRobinTest {

    @Test
    public void testListConstuctor() throws Exception {
        RoundRobin<String> rr = new RoundRobin<>(Arrays.asList(
                "Hello,","Wolrd!"
        ));
    }

    @Test
    public void testIsEmpty() throws Exception {
        RoundRobin<String> rr = new RoundRobin<>();
        rr.isEmpty();
    }

    @Test
    public void testEnqueue() throws Exception {
        RoundRobin<String> rr = new RoundRobin<>();
        rr.enqueue("Hello!");
        rr.enqueue(null);
    }

    @Test
    public void testEnqueueFirst() throws Exception {
        RoundRobin<String> rr = new RoundRobin<>();
        rr.enqueueFirst("World!");
    }
}