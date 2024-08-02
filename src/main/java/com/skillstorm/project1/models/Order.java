package com.skillstorm.project1.models;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Used for the creation of inventory entries
@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    //ManyToOne relationship with boardgames because each order entry is associated with only one boardgame
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardgame_id")
    private BoardGame boardgame;

    //ManyToOne relationship with warehouse because each order entry is associated with only one warehouse
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouse;

    @Column
    private int quantity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;


    public Order(int order_id, BoardGame boardgame, Warehouse warehouse, int quantity, LocalDate date) {
        this.order_id = order_id;
        this.boardgame = boardgame;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.date = date;
    }

    public Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
    
}
