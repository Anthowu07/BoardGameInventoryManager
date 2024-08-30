import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import Orders from './Orders';
import { createOrder } from '../services/orderApi';
import { getBoardGames } from '../services/boardgameApi';
import { getWarehouses } from '../services/warehouseApi';

// Mock the API calls
jest.mock('../services/orderApi');
jest.mock('../services/boardgameApi');
jest.mock('../services/warehouseApi');

//Mock Data
const mockBoardGames = [
    { boardgame_id: 1, name: 'Catan', publisher: 'Kosmos', reorder_quantity: 5 },
];
const mockWarehouses = [
    { warehouse_id: 1, name: 'Warehouse 1', capacity: 100, num_items: 10 },
];

beforeEach(() => {
    createOrder.mockResolvedValue({});
    getBoardGames.mockResolvedValue(mockBoardGames);
    getWarehouses.mockResolvedValue(mockWarehouses);
    render(<Orders />);
});

afterEach(() => {
    jest.clearAllMocks();
    jest.resetAllMocks();
});

describe('Place Order Form', () => {
    //Tests placing an order
    test('handles editing an inventory entry and the form buttons', async () => {

        // // Click "Edit" button for the first inventory entry
        // fireEvent.click(screen.getByDisplayValue('Select a board game'));

        // // Check if the dropdown options are visible
        // await waitFor(() => {
        //     expect(screen.getAllByRole('option').length).toBeGreaterThan(1);
        // });

        // // Click on the "Catan" option on the dropdown
        // fireEvent.click(screen.getByText('Catan'));

        // // Click "Edit" button for the first inventory entry
        // fireEvent.click(screen.getByDisplayValue('Select a warehouse'));

        // // Click on the "Warehouse 1" option on the dropdown
        // fireEvent.click(screen.getByText('Warehouse 1'));

        // // Click "Edit" button for the first inventory entry
        // fireEvent.click(screen.getByDisplayValue('Create Order'));

        // // Check that updateInventory was called
        // expect(updateInventory).toHaveBeenCalledWith({
        //     inventory_id: 1,
        //     boardgame: { name: 'Catan', publisher: 'Kosmos' },
        //     name: "Catan",
        //     publisher: "Kosmos",
        //     quantity_available: "20",
        //     warehouse: { name: 'Warehouse' }
        // });

        // // Check if confirmation message is shown
        // await waitFor(() => {
        //     expect(screen.getByText('Order Placed!')).toBeInTheDocument();
        // });
    });
});