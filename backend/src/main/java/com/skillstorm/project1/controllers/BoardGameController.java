package com.skillstorm.project1.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.services.BoardGameService;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {

    @Autowired
    private BoardGameService service;

    // public BoardGameController(BoardGameService service) {
    //     this.service = service;
    // }

    @GetMapping
    public Iterable<BoardGame> findAllBoardGames(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardGame> findById(@PathVariable int id){
        Optional<BoardGame> boardgame = service.findById(id);
        if(boardgame.isPresent()){
            return ResponseEntity.ok(boardgame.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST Method to add a board game entry
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<BoardGame> Create(@RequestBody BoardGame boardgame){
        Optional<BoardGame> existingBoardGame = this.service.findById(boardgame.getBoardgame_id());

        //Returns code 409 CONFLICT if boardgame id alredy exists in database
        if(existingBoardGame.isPresent()){

            return new ResponseEntity<>(boardgame, HttpStatus.CONFLICT);
        }
        boardgame = service.save(boardgame);
        return new ResponseEntity<>(boardgame, HttpStatus.CREATED);
    }

    //PUT Method to update/change board game entry
    //The method checks if the value exists in the table before attempting the method
    @PutMapping("/{id}")
    public ResponseEntity<BoardGame> updateBoardGame(@PathVariable("id") int id, @RequestBody BoardGame boardgame){
        boardgame.setBoardgame_id(id);
        Optional<BoardGame> existingBoardGame = this.service.findById(boardgame.getBoardgame_id());
        BoardGame updatedBoardGame = boardgame;
        if(existingBoardGame.isPresent()){
            updatedBoardGame = this.service.save(boardgame);
            return new ResponseEntity<>(updatedBoardGame, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE method to erase data from table
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}
