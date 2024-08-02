import React from 'react';
import './Card.css'
import { Link } from 'react-router-dom';


const Card = ({ warehouse }) => {
  return (

    <div className="card">
      <div className="container">
        <h2><b>{warehouse.name}</b></h2>
        <p>Number of Items: {warehouse.num_items}</p>
        <p>Capacity: {warehouse.capacity}</p>
        <Link to={`/warehouses/${warehouse.warehouse_id}/inventories`}>View Inventory</Link>
      </div>
    </div>
  );
}

export default Card;