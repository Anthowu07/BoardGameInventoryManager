package com.skillstorm.project1.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.repositories.BoardGameRepo;

public class BoardGameServiceTest {

    @Mock
    private BoardGameRepo boardGameRepo;

    @InjectMocks
    private BoardGameService boardGameService;
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
    public void findAllTest() {
        List<BoardGame> expectedBoardGames = Arrays.asList(new BoardGame(), new BoardGame());

        when(boardGameRepo.findAll()).thenReturn(expectedBoardGames);

        Iterable<BoardGame> response = boardGameService.findAll();

        assertEquals(expectedBoardGames, response);
    }

    @Test
    public void findByIdTest() {
        BoardGame expectedBoardGame = new BoardGame();
        int gameId = 4;

        when(boardGameRepo.findById(gameId)).thenReturn(Optional.of(expectedBoardGame));

        Optional<BoardGame> response = boardGameService.findById(gameId);

        assertEquals(Optional.of(expectedBoardGame), response);
    }

    @Test
    public void saveTest() {
        BoardGame inputBoardGame = new BoardGame();
        BoardGame savedBoardGame = new BoardGame();

        when(boardGameRepo.save(inputBoardGame)).thenReturn(savedBoardGame);

        BoardGame response = boardGameService.save(inputBoardGame);

        assertEquals(savedBoardGame, response);
    }

    @Test
    public void deleteByIdTest() {
        boardGameService.deleteById(1);
    }

}
