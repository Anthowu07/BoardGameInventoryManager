package com.skillstorm.project1.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.skillstorm.project1.models.Order;

public class OrderRequestTest {

    private OrderRequest orderRequest;
    private OrderRequest orderRequest2;

    @Before
    public void setup() {
        orderRequest = new OrderRequest();
        orderRequest2 = new OrderRequest(2, 2);
    }

    @After
    public void teardown() throws Exception {
        orderRequest = null;
        orderRequest2 = null;
    }

    @Test
    public void gettersAndSettersTest() {
        orderRequest.setBoardgame_id(1);
        orderRequest.setWarehouse_id(1);

        assertEquals(1, orderRequest.getBoardgame_id());
        assertEquals(1, orderRequest.getWarehouse_id());

        assertEquals(2, orderRequest2.getBoardgame_id());
        assertEquals(2, orderRequest2.getWarehouse_id());
    }
}
