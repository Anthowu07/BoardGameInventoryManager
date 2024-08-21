package com.skillstorm.project1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;

import org.junit.jupiter.api.Test;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;

public class InventroyTest {

    Inventory inventory1;
    Inventory inventory2;
    Inventory inventory3;

    BoardGame boardGame1;
    BoardGame boardGame2;

    Warehouse warehouse1;
    Warehouse warehouse2;

    @Test
    public void testInventory() {
        boardGame1 = new BoardGame(0, "Chess", "India", 5);
        boardGame1 = new BoardGame(1, "Checkers", "England", 10);

        warehouse1 = new Warehouse(0, "warehouse1", 100, 30);
        warehouse1 = new Warehouse(1, "warehouse2", 200, 60);

        inventory1 = new Inventory();
        inventory1.setInventory_id(0);
        inventory1.setBoardgame(boardGame1);
        inventory1.setWarehouse(warehouse1);
        inventory1.setQuantity_available(4);
        inventory1.setReorder_point(3);
        inventory1.setMaximum_stock_level(40);
        inventory1.setMinimum_stock_level(0);

        inventory2 = new Inventory(1, boardGame2, warehouse2, 2, 3);
        inventory2.setMaximum_stock_level(30);
        inventory2.setMinimum_stock_level(0);

        inventory3 = new Inventory(2, boardGame1, warehouse1, 5, 10, 20, 1);

        assertEquals(0, inventory1.getInventory_id());
        assertEquals(boardGame1, inventory1.getBoardgame());
        assertEquals(warehouse1, inventory1.getWarehouse());
        assertEquals(4, inventory1.getQuantity_available());
        assertEquals(3, inventory1.getReorder_point());
        assertEquals(40, inventory1.getMaximum_stock_level());
        assertEquals(0, inventory1.getMinimum_stock_level());

        assertEquals(1, inventory2.getInventory_id());
        assertEquals(boardGame2, inventory2.getBoardgame());
        assertEquals(warehouse2, inventory2.getWarehouse());
        assertEquals(2, inventory2.getQuantity_available());
        assertEquals(3, inventory2.getReorder_point());
        assertEquals(30, inventory2.getMaximum_stock_level());
        assertEquals(0, inventory2.getMinimum_stock_level());

        assertEquals(2, inventory3.getInventory_id());
        assertEquals(boardGame1, inventory3.getBoardgame());
        assertEquals(warehouse1, inventory3.getWarehouse());
        assertEquals(5, inventory3.getQuantity_available());
        assertEquals(10, inventory3.getReorder_point());
        assertEquals(20, inventory3.getMaximum_stock_level());
        assertEquals(1, inventory3.getMinimum_stock_level());

    }

    @After
    public void teardownBoardGamesAndWarehousesAndInventory() {
        warehouse1 = null;
        warehouse2 = null;

        boardGame1 = null;
        boardGame2 = null;

        inventory1 = null;
        inventory2 = null;
    }

}
