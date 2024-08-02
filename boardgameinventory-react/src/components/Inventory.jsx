import React, { useEffect, useState } from 'react';
import { getInventory, createInventory } from '../services/inventoryApi';
import './Inventory.css'
const InventoryList = () => {

    const [inventory, setInventory] = useState([]);
    const [name, setName] = useState('');
    const [boardgame_name, setBoardGame_Name] = useState(''); //prolly gonna have to change this to id
    const [warehouse_name, setWarehouse_Name] = useState(''); //this too
    const [quantity_available, setQuantity_Available] = useState(0);

    const [error, setError] = useState(null);

    //Handle GET Request
    useEffect(() => {
        const fetchInventory = async () => {
            try {
                const data = await getInventory();
                setInventory(data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchInventory();
    }, []);

    //Handle POST Request
    const handleInventory = async () => {
        const Inventory = { name, publisher, reorder_quantity };
        try {
            await Inventory(newInventory);
            const data = await getInventory();
            setInventory(data);
        } catch (error) {
            setError(error.message);
        }
    };

    return (
        <div>
            <h2>Inventory Data</h2>
            {error && <div>Error: {error}</div>}
            <table>
                <thead>
                    <tr>
                        <th>Inventory ID</th>
                        <th>Board Game Name</th>
                        <th>Publisher</th>
                        <th>Quantity Available</th>
                        <th>Reorder Point</th>
                        <th>Maximum Stock Level</th>
                        <th>Minimum Stock Level</th>
                        <th>Warehouse Name</th>
                    </tr>
                </thead>
                <tbody>
                    {inventory.map((inventory) => (
                        <tr key={inventory.inventory_id}>
                            <td>{inventory.inventory_id}</td>
                            <td>{inventory.boardgame.name}</td>
                            <td>{inventory.boardgame.publisher}</td>
                            <td>{inventory.quantity_available}</td>
                            <td>{inventory.reorder_point}</td>
                            <td>{inventory.maximum_stock_level}</td>
                            <td>{inventory.minimum_stock_level}</td>
                            <td>{inventory.warehouse.name}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );

};



export default InventoryList;