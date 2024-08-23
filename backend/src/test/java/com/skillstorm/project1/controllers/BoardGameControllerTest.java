package com.skillstorm.project1.controllers;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

        Iterable<BoardGame> actualBoardGames = boardGameController.findAllBoardGames();

        assertEquals(actualBoardGames, expectedBoardGames);
    }

}
