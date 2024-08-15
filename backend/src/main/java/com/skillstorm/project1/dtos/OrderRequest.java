package com.skillstorm.project1.dtos;

//Used to grab only board game and warehouse ids
public class OrderRequest {

    private int boardgame_id;
    private int warehouse_id;
    
    public OrderRequest() {
    }
    public OrderRequest(int boardgame_id, int warehouse_id) {
        this.boardgame_id = boardgame_id;
        this.warehouse_id = warehouse_id;
    }
    public int getBoardgame_id() {
        return boardgame_id;
    }
    public void setBoardgame_id(int boardgame_id) {
        this.boardgame_id = boardgame_id;
    }
    public int getWarehouse_id() {
        return warehouse_id;
    }
    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

}
