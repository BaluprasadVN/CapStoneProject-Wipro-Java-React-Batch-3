import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import App from './pages/App.jsx'
import Login from './pages/Login.jsx'
import Register from './pages/Register.jsx'
import Search from './pages/Search.jsx'
import Trip from './pages/Trip.jsx'
import Checkout from './pages/Checkout.jsx'
import Landing from "./pages/Landing.jsx"

ReactDOM.createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />}>
        <Route index element={<Navigate to="/search" />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="search" element={<Search />} />
        <Route path="trip/:id" element={<Trip />} />
        <Route path="checkout/:bookingId" element={<Checkout />} />
      </Route>
      <Route index element={<Landing />} />
<Route path="search" element={<Search />} />
    </Routes>
  </BrowserRouter>
)
