package com.skillstorm.project1.models;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    Order order1;
    Order order2;

    BoardGame boardGame1;
    BoardGame boardGame2;

    Warehouse warehouse1;
    Warehouse warehouse2;

    LocalDate date1; 
    LocalDate date2;

    @Before
    public void setup() {
        boardGame1 = new BoardGame(0, "Chess", "India", 5);
        boardGame1 = new BoardGame(1, "Checkers", "England", 10);

        warehouse1 = new Warehouse(0, "warehouse1", 100, 30);
        warehouse1 = new Warehouse(1, "warehouse2", 200, 60);

        date1 = LocalDate.now();
        date2 = LocalDate.now();

        order1 = new Order();
        order1.setOrder_id(0);
        order1.setBoardgame(boardGame1);
        order1.setWarehouse(warehouse1);
        order1.setQuantity(3);
        order1.setDate(date1);

        order2 = new Order(1, boardGame2, warehouse2, 5, date2);
    }

    @Test 
    public void testOrder() {
        assertEquals(0, order1.getOrder_id());
        assertEquals(boardGame1, order1.getBoardgame());
        assertEquals(warehouse1, order1.getWarehouse());
        assertEquals(3, order1.getQuantity());
        assertEquals(date1, order1.getDate());

        assertEquals(1, order2.getOrder_id());
        assertEquals(boardGame2, order2.getBoardgame());
        assertEquals(warehouse2, order2.getWarehouse());
        assertEquals(5, order2.getQuantity());
        assertEquals(date2, order2.getDate());

    }

    @After
    public void teardown() {
        warehouse1 = null;
        warehouse2 = null;

        boardGame1 = null;
        boardGame2 = null;

        order1 = null;
        order2 = null;
    }

}
