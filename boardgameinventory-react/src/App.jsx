import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BoardGameList from './components/BoardGames';
import Navbar from './Navbar';

import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {


  return (
    <>
      <Router>

        <Navbar />
        <Routes>
          <Route path="/boardgames" element={<BoardGameList />} />
        </Routes>

      </Router>
    </>
  )
}

export default App
