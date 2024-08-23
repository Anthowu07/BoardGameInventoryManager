package com.skillstorm.project1.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Test;

import com.skillstorm.project1.models.BoardGame;

public class BoardGameTest {

    BoardGame boardGame1;
    BoardGame boardGame2;

    @Test
    public void testBoardGameFields() {
        boardGame1 = new BoardGame();
        boardGame1.setBoardgame_id(0);
        boardGame1.setName("Checkers");
        boardGame1.setPublisher("England");
        boardGame1.setReorder_quantity(5);


        boardGame2 = new BoardGame(1, "Chess", "India", 10);
       
        assertEquals(0, boardGame1.getBoardgame_id());
        assertEquals("Checkers", boardGame1.getName());
        assertEquals("England", boardGame1.getPublisher());
        assertEquals(5, boardGame1.getReorder_quantity());

        assertEquals(1, boardGame2.getBoardgame_id());
        assertEquals("Chess", boardGame2.getName());
        assertEquals("India", boardGame2.getPublisher());
        assertEquals(10, boardGame2.getReorder_quantity());

    }
    
    @After
    public void teardownBoardGames() {
        boardGame1 = null;
        boardGame2 = null;
    }

}
