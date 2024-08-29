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

import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.InventoryRepo;

public class InventoryServiceTest {

    @Mock
    private InventoryRepo inventoryRepo;

    @InjectMocks
    private InventoryService inventoryService;
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
        List<Inventory> expectedInventories = Arrays.asList(new Inventory(), new Inventory());

        when(inventoryRepo.findAll()).thenReturn(expectedInventories);

        Iterable<Inventory> response = inventoryService.findAll();

        assertEquals(expectedInventories, response);
    }

    @Test
    public void findByIdTest() {
        Inventory expectedInventory = new Inventory();
        int inventoryId = 4;

        when(inventoryRepo.findById(inventoryId)).thenReturn(Optional.of(expectedInventory));

        Optional<Inventory> response = inventoryService.findById(inventoryId);

        assertEquals(Optional.of(expectedInventory), response);
    }

    @Test
    public void saveTest() {
        Inventory inputInventory = new Inventory();
        Inventory savedInventory = new Inventory();

        when(inventoryRepo.save(inputInventory)).thenReturn(savedInventory);

        Inventory response = inventoryService.save(inputInventory);

        assertEquals(savedInventory, response);
    }

    @Test
    public void deleteByIdTest() {
        inventoryService.deleteById(3);
    }
}
