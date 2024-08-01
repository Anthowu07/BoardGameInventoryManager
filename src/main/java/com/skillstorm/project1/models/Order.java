package com.skillstorm.project1.models;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//TODO: Finish setting all columns, getters and setters, tostring
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<BoardGame> boardgames;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<Warehouse> warehouses;

    @Column
    private int quantity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

}
