import React, { useEffect, useState } from 'react';
import { getWarehouses, createWarehouse } from '../services/warehouseApi';
import Card from './Card';
const WarehouseList = () => {

    const [warehouses, setWarehouse] = useState([]);
    const [name, setName] = useState('');
    const [capacity, setCapacity] = useState(0);
    const [num_items, setNum_Items] = useState(0);
    const [error, setError] = useState(null);

    //Handle GET Request
    useEffect(() => {
        const fetchWarehouses = async () => {
            try {
                const data = await getWarehouses();
                setWarehouse(data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchWarehouses();
    }, []);

    //Handle POST Request
    const handleCreateWarehouse = async () => {
        const newWarehouse = { name, capacity, num_items };
        try {
            await createWarehouse(newWarehouse);
            const data = await getWarehouses();
            setWarehouse(data);
        } catch (error) {
            setError(error.message);
        }
    };

    return (

        <div className="cards">
            {error && <div>Error: {error}</div>}
            {warehouses.map((warehouse) => (
                <Card key={warehouse.warehouse_id} warehouse={warehouse} />
            ))}
        </div>
    );
};



export default WarehouseList;