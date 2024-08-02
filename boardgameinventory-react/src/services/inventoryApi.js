const INVENTORY_API_URL = 'http://localhost:8080/inventory';

export const getInventory = async ()=>{
    const response = await fetch(INVENTORY_API_URL);
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return await response.json();
};

export const createInventory = async (inventory) => {
    const response = await fetch(INVENTORY_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(inventory),
    });
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return await response.json();
};