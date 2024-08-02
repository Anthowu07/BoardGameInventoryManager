import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BoardGameList from './components/BoardGames';
import WarehouseList from './components/Warehouses';
import JoinTable from './components/JoinTable';
import InventoryList from './components/Inventory';
import Navbar from './Navbar';

import { useState } from 'react'

import './App.css'

function App() {


  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/boardgames" element={<BoardGameList />} />
          <Route path="/warehouses" element={<WarehouseList />} />
          <Route path='/warehouses/:id/inventories' element={<JoinTable />} />
          <Route path="/inventory" element={<InventoryList />} />
        </Routes>
      </Router >
    </>
  )
}

export default App
