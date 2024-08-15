package com.skillstorm.project1.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.repositories.BoardGameRepo;

@Service
public class BoardGameService {
    private BoardGameRepo repo;

    public BoardGameService(BoardGameRepo repo) {
        this.repo = repo;
    }

    public Iterable<BoardGame> findAll(){
        return repo.findAll();
    }

    public Optional<BoardGame> findById(int id){
        return repo.findById(id);
    }

    //Used for PUT/POST requests
    public BoardGame save(BoardGame boardgame){
        return repo.save(boardgame);
    }

    //Used for DELETE requests
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
