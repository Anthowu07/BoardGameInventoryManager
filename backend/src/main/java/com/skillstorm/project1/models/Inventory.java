package com.skillstorm.project1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Used as a middleman between a product and it's warehouse location
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventory_id;

    //ManyToOne relationship with boardgames because each inventory entry is associated with only one boardgame
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardgame_id")
    private BoardGame boardgame;

    //ManyToOne relationship with warehouse because each inventory entry is associated with only one warehouse
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouse;

    @Column
    private int quantity_available;

    @Column
    private int reorder_point;

    @Column
    private int maximum_stock_level;

    @Column
    private int minimum_stock_level;

    public Inventory() {
    }

    public Inventory(int inventory_id, BoardGame boardgame, Warehouse warehouse, int quantity_available,
            int reorder_point) {
        this.inventory_id = inventory_id;
        this.boardgame = boardgame;
        this.warehouse = warehouse;
        this.quantity_available = quantity_available;
        this.reorder_point = reorder_point;
    }
    
    public Inventory(int inventory_id, BoardGame boardgame, Warehouse warehouse, int quantity_available,
            int reorder_point, int maximum_stock_level, int minimum_stock_level) {
        this.inventory_id = inventory_id;
        this.boardgame = boardgame;
        this.warehouse = warehouse;
        this.quantity_available = quantity_available;
        this.reorder_point = reorder_point;
        this.maximum_stock_level = maximum_stock_level;
        this.minimum_stock_level = minimum_stock_level;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public BoardGame getBoardgame() {
        return boardgame;
    }

    public void setBoardgame(BoardGame boardgame) {
        this.boardgame = boardgame;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    // @Override
    // public String toString() {
    //     return "Inventory [inventory_id=" + inventory_id + ", boardgame=" + boardgame + ", warehouse=" + warehouse
    //             + ", quantity_available=" + quantity_available + ", reorder_point=" + reorder_point
    //             + ", maximum_stock_level=" + maximum_stock_level + ", minimum_stock_level=" + minimum_stock_level + "]";
    // }
}
