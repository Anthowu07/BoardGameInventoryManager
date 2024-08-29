package com.skillstorm.project1.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.project1.dtos.InventoryRequest;
import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.BoardGameService;
import com.skillstorm.project1.services.InventoryService;
import com.skillstorm.project1.services.WarehouseService;

public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @Mock
    private BoardGameService boardGameService;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private InventoryController inventoryController;
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
    public void findAllInventoriesTest() {
        Iterable<Inventory> expectedInventories = Arrays.asList(new Inventory(), new Inventory());

        when(inventoryService.findAll()).thenReturn(expectedInventories);

        Iterable<Inventory> response = inventoryController.findAllInventories();

        assertEquals(expectedInventories, response);
    }

    @Test
    public void findByIdSuccessTest() {
        int inventoryId = 1;
        Inventory expectedInventory = new Inventory();

        when(inventoryService.findById(inventoryId))
        
        .thenReturn(Optional.of(expectedInventory));

        ResponseEntity<Inventory> response = inventoryController.findById(inventoryId);

        assertEquals(expectedInventory, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findByIdFailureTest() {
        int inventoryId = 1;
        Inventory expectedInventory = new Inventory();

        when(inventoryService.findById(inventoryId))
        
        .thenReturn(Optional.of(expectedInventory));

        ResponseEntity<Inventory> response = inventoryController.findById(5);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void createFailureNoBoardGameTest() {
        Inventory inputInventory = new Inventory();
        Inventory savedInventory = new Inventory();
        InventoryRequest request = new InventoryRequest();

        when(inventoryService.save(inputInventory)).thenReturn(savedInventory);

        ResponseEntity<?> response = inventoryController.Create(request);

        assertEquals("Board game with that ID is not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createFailureNoWarehouseTest() {
        Inventory inputInventory = new Inventory();
        Inventory savedInventory = new Inventory();
        InventoryRequest request = new InventoryRequest();
        BoardGame savedBoardGame = new BoardGame();

        int gameId = 5;
        request.setBoardgame_id(gameId);


        when(inventoryService.save(inputInventory)).thenReturn(savedInventory);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));

        ResponseEntity<?> response = inventoryController.Create(request);

        assertEquals("Warehouse with that ID is not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createSuccessTest() {
        Inventory inputInventory = new Inventory();
        Inventory savedInventory = new Inventory();
        InventoryRequest request = new InventoryRequest();
        BoardGame savedBoardGame = new BoardGame();
        Warehouse savedWarehouse = new Warehouse();

        int gameId = 5;
        request.setBoardgame_id(gameId);

        int warehouseId = 10;
        request.setWarehouse_id(warehouseId);


        when(inventoryService.save(inputInventory)).thenReturn(savedInventory);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));
        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(savedWarehouse));

        ResponseEntity<?> response = inventoryController.Create(request);

        //assertEquals("Warehouse with that ID is not found", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
  

    @Test
    public void updateInventoryTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setNum_items(30);

        Inventory inputInventory = new Inventory();
        inputInventory.setWarehouse(warehouse);

        int inventoryId = 13;
        inputInventory.setInventory_id(inventoryId);


        when(inventoryService.save(inputInventory)).thenReturn(inputInventory);
        when(inventoryService.findById(inventoryId)).thenReturn(Optional.of(inputInventory));

        
        ResponseEntity<Inventory> response = inventoryController.updateInventory(inventoryId, inputInventory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), inputInventory);
    }


    @Test
    public void updateInventoryFailureTest() {
        Inventory inputInventory = new Inventory();
        int inventoryId = 13;
        inputInventory.setInventory_id(inventoryId);


        when(inventoryService.save(inputInventory)).thenReturn(inputInventory);
        when(inventoryService.findById(inventoryId)).thenReturn(Optional.of(inputInventory));

        
        ResponseEntity<Inventory> response = inventoryController.updateInventory(inventoryId + 1, inputInventory);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testDeleteById() {
        Warehouse warehouse = new Warehouse();
        warehouse.setNum_items(30);

        Inventory inputInventory = new Inventory();
        inputInventory.setWarehouse(warehouse);
        inputInventory.setQuantity_available(5);
        int inventoryId = 17;

        when(inventoryService.findById(inventoryId)).thenReturn(Optional.of(inputInventory));

        inventoryController.deleteById(inventoryId);

    }
}
