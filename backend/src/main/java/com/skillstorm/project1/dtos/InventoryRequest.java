package com.skillstorm.project1.dtos;

public class InventoryRequest {

    private int boardgame_id;
    private int warehouse_id;
    private int quantity_available;
    private int reorder_point;
    private int maximum_stock_level;
    private int minimum_stock_level;

    
    
    public InventoryRequest() {
    }

    public InventoryRequest(int boardgame_id, int warehouse_id, int quantity_available, int reorder_point,
            int maximum_stock_level, int minimum_stock_level) {
        this.boardgame_id = boardgame_id;
        this.warehouse_id = warehouse_id;
        this.quantity_available = quantity_available;
        this.reorder_point = reorder_point;
        this.maximum_stock_level = maximum_stock_level;
        this.minimum_stock_level = minimum_stock_level;
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
    public int getQuantity_available() {
        return quantity_available;
    }
    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }
    public int getReorder_point() {
        return reorder_point;
    }
    public void setReorder_point(int reorder_point) {
        this.reorder_point = reorder_point;
    }
    public int getMaximum_stock_level() {
        return maximum_stock_level;
    }
    public void setMaximum_stock_level(int maximum_stock_level) {
        this.maximum_stock_level = maximum_stock_level;
    }
    public int getMinimum_stock_level() {
        return minimum_stock_level;
    }
    public void setMinimum_stock_level(int minimum_stock_level) {
        this.minimum_stock_level = minimum_stock_level;
    }
}
