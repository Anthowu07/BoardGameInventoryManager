package com.skillstorm.project1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.BoardGame;

@Repository
public interface BoardGameRepo extends JpaRepository<BoardGame, Integer>{
    
}
