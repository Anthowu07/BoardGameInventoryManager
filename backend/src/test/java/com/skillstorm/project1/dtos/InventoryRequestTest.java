package com.skillstorm.project1.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryRequestTest {

    private InventoryRequest inventoryRequest;
    private InventoryRequest inventoryRequest2;

    @Before
    public void setup() {
        inventoryRequest = new InventoryRequest();
        inventoryRequest2 = new InventoryRequest(2, 2, 10, 3, 20, 5);
    }

    @After
    public void teardown() throws Exception {
        inventoryRequest = null;
        inventoryRequest2 = null;
    }

    @Test
    public void gettersAndSettersTest() {
        inventoryRequest.setBoardgame_id(1);
        inventoryRequest.setWarehouse_id(1);
        inventoryRequest.setQuantity_available(5);
        inventoryRequest.setReorder_point(2);
        inventoryRequest.setMaximum_stock_level(30);
        inventoryRequest.setMinimum_stock_level(3);

        assertEquals(1, inventoryRequest.getBoardgame_id());
        assertEquals(1, inventoryRequest.getWarehouse_id());
        assertEquals(5, inventoryRequest.getQuantity_available());
        assertEquals(2, inventoryRequest.getReorder_point());
        assertEquals(30, inventoryRequest.getMaximum_stock_level());
        assertEquals(3, inventoryRequest.getMinimum_stock_level());

        assertEquals(2, inventoryRequest2.getBoardgame_id());
        assertEquals(2, inventoryRequest2.getWarehouse_id());
        assertEquals(10, inventoryRequest2.getQuantity_available());
        assertEquals(3, inventoryRequest2.getReorder_point());
        assertEquals(20, inventoryRequest2.getMaximum_stock_level());
        assertEquals(5, inventoryRequest2.getMinimum_stock_level());
    }
}
