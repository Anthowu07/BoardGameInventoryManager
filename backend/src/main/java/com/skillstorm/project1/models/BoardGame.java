package com.skillstorm.project1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "boardgame")
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardgame_id;
    
    @Column(length=50)
    private String name;

    @Column(length=50)
    private String publisher;

    @Column
    private int reorder_quantity;

    public BoardGame() {
    }

    public BoardGame(int boardgame_id, String name, String publisher, int reorder_quantity) {
        this.boardgame_id = boardgame_id;
        this.name = name;
        this.publisher = publisher;
        this.reorder_quantity = reorder_quantity;
    }

    public int getBoardgame_id() {
        return boardgame_id;
    }

    public void setBoardgame_id(int boardgame_id) {
        this.boardgame_id = boardgame_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getReorder_quantity() {
        return reorder_quantity;
    }

    public void setReorder_quantity(int reorder_quantity) {
        this.reorder_quantity = reorder_quantity;
    } 

    // @Override
    // public String toString() {
    //     return "BoardGame [boardgame_id=" + boardgame_id + ", name=" + name + ", publisher=" + publisher
    //             + ", reorder_quantity=" + reorder_quantity + "]";
    // }
}
