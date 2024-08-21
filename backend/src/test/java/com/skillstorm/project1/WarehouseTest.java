package com.skillstorm.project1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;

import org.junit.jupiter.api.Test;

import com.skillstorm.project1.models.Warehouse;

public class WarehouseTest {

    Warehouse warehouse1;
    Warehouse warehouse2;

    @Test
    public void testWarehouse() {
        warehouse1 = new Warehouse();
        warehouse1.setWarehouse_id(0);
        warehouse1.setName("warehouse1");
        warehouse1.setCapacity(100);
        warehouse1.setNum_items(30);

        warehouse2 = new Warehouse(1, "warehouse2", 200, 60);

        assertEquals(0, warehouse1.getWarehouse_id());
        assertEquals("warehouse1", warehouse1.getName());
        assertEquals(100, warehouse1.getCapacity());
        assertEquals(30, warehouse1.getNum_items());

        assertEquals(1, warehouse2.getWarehouse_id());
        assertEquals("warehouse2", warehouse2.getName());
        assertEquals(200, warehouse2.getCapacity());
        assertEquals(60, warehouse2.getNum_items());
    }

    @After
    public void tearDownWarehouses() {
        warehouse1 = null;
        warehouse2 = null;
    }

}
