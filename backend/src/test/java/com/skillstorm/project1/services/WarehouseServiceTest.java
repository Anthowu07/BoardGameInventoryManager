package com.skillstorm.project1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.BoardGameRepo;
import com.skillstorm.project1.repositories.InventoryRepo;
import com.skillstorm.project1.repositories.WarehouseRepo;

public class WarehouseServiceTest {

    @Mock
    private WarehouseRepo warehouseRepo;

    @Mock
    private InventoryRepo inventoryRepo;

    @InjectMocks
    private WarehouseService warehouseService;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void findAllTest() {
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        when(warehouseRepo.findAll()).thenReturn(expectedWarehouses);

        Iterable<Warehouse> response = warehouseService.findAll();

        assertEquals(expectedWarehouses, response);
    }

    @Test
    public void findByIdTest() {
        Warehouse expectedWarehouse = new Warehouse();
        int warehouseId = 4;

        when(warehouseRepo.findById(warehouseId)).thenReturn(Optional.of(expectedWarehouse));

        Optional<Warehouse> response = warehouseService.findById(warehouseId);

        assertEquals(Optional.of(expectedWarehouse), response);
    }

    @Test
    public void saveTest() {
        Warehouse inputWarehouse = new Warehouse();
        Warehouse savedWarehouse = new Warehouse();

        when(warehouseRepo.save(inputWarehouse)).thenReturn(savedWarehouse);

        Warehouse response = warehouseService.save(inputWarehouse);

        assertEquals(savedWarehouse, response);
    }

    @Test
    public void deleteByIdTest() {
        warehouseService.deleteById(3);
    }

    @Test
    public void getInventoriesByWarehouseIdTest() {
        List<Inventory> inventoryList = Arrays.asList(new Inventory(), new Inventory());
        int warehouseId = 6;

        when(inventoryRepo.findByWarehouseId(warehouseId)).thenReturn(inventoryList);

        List<Inventory> response = warehouseService.getInventoriesByWarehouseId(warehouseId);

        assertEquals(inventoryList, response);
    }
}
