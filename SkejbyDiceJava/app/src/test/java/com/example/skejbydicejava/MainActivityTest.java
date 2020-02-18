package com.example.skejbydicejava;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivityTest {
    private Player p1, p2, p3, p4;
    private LuckyDie l1, l2, l3, l4;

    @Before
    public void setUp() {
        p1 = new Player("Søren", 1, "brown");
        p2 = new Player("Nikolaj", 2, "green");
        p3 = new Player("Bjørn", 3, "blue");
        p4 = new Player("Christian", 4, "red");

        l1 = new LuckyDie("brown");
        l2 = new LuckyDie("green");
        l3 = new LuckyDie("blue");
        l4 = new LuckyDie("red");
    }

    @Test
    public void testPlayer() {
        assertEquals("Søren", p1.getName());
        assertEquals(1, p1.getLuckyDieNumber());
        assertEquals(1, p1.getPos());
        assertEquals(0, p1.getSips());
        assertEquals(R.drawable.token1, p1.getToken());
    }

    @Test
    public void testLuckyDie() {
        assertEquals(1,l1.getNumber());
        assertEquals(R.drawable.brownl1,l1.getState());
        assertEquals(R.drawable.gl1,l2.getState());
        assertEquals(R.drawable.bluel1,l3.getState());
        assertEquals(R.drawable.redl1,l4.getState());
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}