package com.skillstorm.project1.controllers;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

public class WarehouseControllerTest {

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;
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
    public void findAllWarehousesTest() {
        Iterable<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        when(warehouseService.findAll()).thenReturn(expectedWarehouses);

        Iterable<Warehouse> response = warehouseController.findAllWarehouses();

        assertEquals(expectedWarehouses, response);
    }

    @Test
    public void findByIdSuccessTest() {
        int warehouseId = 1;
        Warehouse expectedWarehouse = new Warehouse();

        when(warehouseService.findById(warehouseId))
        
        .thenReturn(Optional.of(expectedWarehouse));

        ResponseEntity<Warehouse> response = warehouseController.findById(warehouseId);

        assertEquals(expectedWarehouse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findByIdFailureTest() {
        int warehouseId = 1;
        Warehouse expectedWarehouse = new Warehouse();

        when(warehouseService.findById(warehouseId))
        
        .thenReturn(Optional.of(expectedWarehouse));

        ResponseEntity<Warehouse> response = warehouseController.findById(3);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void createConflictTest() {
        Warehouse inputWarehouse = new Warehouse();
        int warehouseId = 7;
        inputWarehouse.setWarehouse_id(warehouseId);

        Warehouse savedWarehouse = new Warehouse();

        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(savedWarehouse));

        ResponseEntity<Warehouse> response = warehouseController.Create(inputWarehouse);

        assertEquals(inputWarehouse, response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void createSuccessTest() {
        int warehouseId = 1;
        Warehouse expectedWarehouse = new Warehouse();
        expectedWarehouse.setWarehouse_id(2);

        when(warehouseService.findById(warehouseId))
        
        .thenReturn(Optional.of(expectedWarehouse));

        ResponseEntity<Warehouse> response = warehouseController.Create(expectedWarehouse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateBoardGameTest() {
        Warehouse inputWarehouse = new Warehouse();
        int warehouseId = 11;
        inputWarehouse.setWarehouse_id(warehouseId);


        when(warehouseService.save(inputWarehouse)).thenReturn(inputWarehouse);
        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(inputWarehouse));

        
        ResponseEntity<Warehouse> response = warehouseController.updateWarehouse(warehouseId, inputWarehouse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), inputWarehouse);
    }


    @Test
    public void updateBoardGameFailureTest() {
        Warehouse inputWarehouse = new Warehouse();
        int warehouseId = 11;
        inputWarehouse.setWarehouse_id(warehouseId);


        when(warehouseService.save(inputWarehouse)).thenReturn(inputWarehouse);
        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(inputWarehouse));

        
        ResponseEntity<Warehouse> response = warehouseController.updateWarehouse(warehouseId + 1, inputWarehouse);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteById() {
        Warehouse inputWarehouse = new Warehouse();
        int gameId = 17;

        warehouseController.deleteById(gameId);

    }

    @Test
    public void getInventoriesByWarehouseIdTest() {
       List<Inventory> expectedInventories = Arrays.asList(new Inventory(), new Inventory());
       int warehouseId = 1;

       when(warehouseService.getInventoriesByWarehouseId(warehouseId)).thenReturn(expectedInventories);

       ResponseEntity<List<Inventory>> response = warehouseController.getInventoriesByWarehouseId(warehouseId);

       assertEquals(expectedInventories, response.getBody());
    }
    
}
