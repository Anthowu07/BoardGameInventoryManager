package com.skillstorm.project1;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skillstorm.project1.controllers.BoardGameController;
import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.repositories.BoardGameRepo;
import com.skillstorm.project1.services.BoardGameService;

@SpringBootTest
public class BoardgameTest {

	// @Autowired
	// static BoardGameRepo boardGameRepo;
	
	// static BoardGameService boardGameService;
    // static BoardGameController boardGameController;

    BoardGame boardGame1;
    BoardGame boardGame2;

    /**
     * BeforeClass and Before
     *      - these are setup methods
     *          used to initialize data you will use within your tests
     * 
     *      BeforeClass - run once before all of the other methods
     *          ex: to setup a datbase connection
     * 
     *      Before - will run right before each test
     *          ex: load data from the database  
     */

    // @BeforeClass
    // public static void setupBoardGameServiceAndController() {
	// 	boardGameService = new BoardGameService(boardGameRepo);
    //     boardGameController = new BoardGameController(boardGameService);
    // }

	@Before
    public void setupBoardGames() {
        boardGame1 = new BoardGame();
        boardGame1.setBoardgame_id(0);
        boardGame1.setName("Checkers");
        boardGame1.setPublisher("English Person");
        boardGame1.setReorder_quantity(5);


        boardGame2 = new BoardGame(1, "Chess", "Indian Person", 10);
    }

    @Test
    public void testBoardGameFields() {
        try {
            assertEquals(0, boardGame1.getBoardgame_id());
            assertEquals("Checkers", boardGame1.getName());
            assertEquals("English Person", boardGame1.getPublisher());
            assertEquals(5, boardGame1.getReorder_quantity());

            assertEquals(1, boardGame2.getBoardgame_id());
            assertEquals("Chess", boardGame2.getName());
            assertEquals("Indian Person", boardGame2.getPublisher());
            assertEquals(10, boardGame2.getReorder_quantity());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testBoardGameFields2() {
        try {
            assertEquals(0, boardGame1.getBoardgame_id());
            assertEquals("Checkers", boardGame1.getName());
            assertEquals("English Person", boardGame1.getPublisher());
            assertEquals(5, boardGame1.getReorder_quantity());

            assertEquals(1, boardGame2.getBoardgame_id());
            assertEquals("Chess", boardGame2.getName());
            assertEquals("Indian Person", boardGame2.getPublisher());
            assertEquals(10, boardGame2.getReorder_quantity());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @After
    public void teardownPeople() {
        boardGame1 = null;
        boardGame2 = null;

    }

    // @AfterClass
    // public static void teardownAgeValidator() {
    //     boardGameRepo = null;
    //     boardGameService = null;
    //     boardGameController = null;
    // }

}
