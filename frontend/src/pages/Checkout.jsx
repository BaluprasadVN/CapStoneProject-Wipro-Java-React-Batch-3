import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import api from '../api'

export default function Checkout(){
  const { bookingId } = useParams()
  const [booking, setBooking] = useState(null)

  const pay = async () => {
    const { data } = await api.post('/payments/checkout', { bookingId: Number(bookingId) })
    setBooking(data)
  }

  useEffect(() => {
    // fetch booking by id (simple)
    api.get('/trips/search', { params: { source: 'Pune', destination: 'Mumbai', dateIso: new Date().toISOString().slice(0,10) } }) // dummy
    setBooking({ id: bookingId, status: 'HELD'})
  }, [bookingId])

  return (
    <div className="col-12 col-md-6">
      <h4>Checkout</h4>
      <p>Booking ID: {bookingId}</p>
      <p>Status: {booking?.status}</p>
      <button className="btn btn-primary" onClick={pay}>Pay (Simulated)</button>
      {booking?.status === 'CONFIRMED' && <div className="alert alert-success mt-2">Payment successful! Ticket issued.</div>}
    </div>
  )
}
