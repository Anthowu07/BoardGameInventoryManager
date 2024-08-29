package com.skillstorm.project1.models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardGameTest {

    BoardGame boardGame1;
    BoardGame boardGame2;

    @Before
    public void setup() {
        boardGame1 = new BoardGame();
        boardGame1.setBoardgame_id(0);
        boardGame1.setName("Checkers");
        boardGame1.setPublisher("England");
        boardGame1.setReorder_quantity(5);


        boardGame2 = new BoardGame(1, "Chess", "India", 10);
    }

    @Test
    public void testBoardGameGetters() {
       
        assertEquals(0, boardGame1.getBoardgame_id());
        assertEquals("Checkers", boardGame1.getName());
        assertEquals("England", boardGame1.getPublisher());
        assertEquals(5, boardGame1.getReorder_quantity());

        assertEquals(1, boardGame2.getBoardgame_id());
        assertEquals("Chess", boardGame2.getName());
        assertEquals("India", boardGame2.getPublisher());
        assertEquals(10, boardGame2.getReorder_quantity());
    }

    @Test
    public void testBoardGameSetters() {
        boardGame1.setBoardgame_id(2);
        boardGame1.setName("Monopoly");
        boardGame1.setPublisher("America");
        boardGame1.setReorder_quantity(20);


        assertEquals(2, boardGame1.getBoardgame_id());
        assertEquals("Monopoly", boardGame1.getName());
        assertEquals("America", boardGame1.getPublisher());
        assertEquals(20, boardGame1.getReorder_quantity());
    }
    
    @After
    public void teardown() {
        boardGame1 = null;
        boardGame2 = null;
    }

}
