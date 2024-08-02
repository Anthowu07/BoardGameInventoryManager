const WAREHOUSE_API_URL = 'http://localhost:8080/warehouses';

export const getWarehouses = async ()=>{
    const response = await fetch(WAREHOUSE_API_URL);
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return await response.json();
};

export const createWarehouse = async (warehouse) => {
    const response = await fetch(WAREHOUSE_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(warehouse),
    });
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return await response.json();
};