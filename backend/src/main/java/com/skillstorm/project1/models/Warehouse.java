package com.skillstorm.project1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouse_id;
 
    @Column(length=50)
    private String name;
 
    @Column
    private int capacity;

    @Column
    private int num_items;
    
    
    public Warehouse() {
    }

    public Warehouse(int warehouse_id, String name, int capacity, int num_items) {
        this.warehouse_id = warehouse_id;
        this.name = name;
        this.capacity = capacity;
        this.num_items = num_items;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNum_items() {
        return num_items;
    }

    public void setNum_items(int num_items) {
        this.num_items = num_items;
    }

    // @Override
    // public String toString() {
    //     return "Warehouse [warehouse_id=" + warehouse_id + ", name=" + name + ", capacity=" + capacity + ", num_items="
    //             + num_items + "]";
    // }

    
}