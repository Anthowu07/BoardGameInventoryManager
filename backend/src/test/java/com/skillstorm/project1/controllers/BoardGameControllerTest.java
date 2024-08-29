package com.skillstorm.project1.controllers;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.services.BoardGameService;

public class BoardGameControllerTest {
    
    @Mock
    private BoardGameService boardGameService;

    @InjectMocks
    private BoardGameController boardGameController;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void findAllBoardGamesTest() {
        Iterable<BoardGame> expectedBoardGames = Arrays.asList(new BoardGame(), new BoardGame());

        when(boardGameService.findAll()).thenReturn(expectedBoardGames);

        Iterable<BoardGame> response = boardGameController.findAllBoardGames();

        assertEquals(expectedBoardGames, response);
    }

    @Test
    public void findByIdSuccessTest() {
        int gameId = 1;
        BoardGame expectedBoardGame = new BoardGame();

        when(boardGameService.findById(gameId))
        
        .thenReturn(Optional.of(expectedBoardGame));

        ResponseEntity<BoardGame> response = boardGameController.findById(gameId);

        assertEquals(expectedBoardGame, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findByIdFailureTest() {
        int gameId = 1;
        BoardGame expectedBoardGame = new BoardGame();

        when(boardGameService.findById(gameId))
        
        .thenReturn(Optional.of(expectedBoardGame));

        ResponseEntity<BoardGame> response = boardGameController.findById(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    
    @Test
    public void createConflictTest() {
        BoardGame inputBoardGame = new BoardGame();
        int gameId = 5;
        inputBoardGame.setBoardgame_id(gameId);

        BoardGame savedBoardGame = new BoardGame();

        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));

        ResponseEntity<BoardGame> response = boardGameController.Create(inputBoardGame);

        assertEquals(inputBoardGame, response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void createSuccessTest() {
        int gameId = 1;
        BoardGame expectedBoardGame = new BoardGame();
        expectedBoardGame.setBoardgame_id(2);

        when(boardGameService.findById(gameId))
        
        .thenReturn(Optional.of(expectedBoardGame));

        ResponseEntity<BoardGame> response = boardGameController.Create(expectedBoardGame);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        
    }

    @Test
    public void updateBoardGameTest() {
        BoardGame inputBoardGame = new BoardGame();
        int gameId = 11;
        inputBoardGame.setBoardgame_id(gameId);


        when(boardGameService.save(inputBoardGame)).thenReturn(inputBoardGame);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(inputBoardGame));

        
        ResponseEntity<BoardGame> response = boardGameController.updateBoardGame(gameId, inputBoardGame);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), inputBoardGame);
    }


    @Test
    public void updateBoardGameFailureTest() {
        BoardGame inputBoardGame = new BoardGame();
        int gameId = 13;
        inputBoardGame.setBoardgame_id(gameId);


        when(boardGameService.save(inputBoardGame)).thenReturn(inputBoardGame);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(inputBoardGame));

        
        ResponseEntity<BoardGame> response = boardGameController.updateBoardGame(gameId + 1, inputBoardGame);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testDeleteById() {
        BoardGame inputBoardGame = new BoardGame();
        int gameId = 17;

        boardGameController.deleteById(gameId);

    }

}
