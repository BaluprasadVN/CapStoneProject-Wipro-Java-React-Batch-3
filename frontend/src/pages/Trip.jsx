import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import api from '../api'

export default function TripPage(){
  const { id } = useParams()
  const [trip, setTrip] = useState(null)
  const [seats, setSeats] = useState([])
  const [selected, setSelected] = useState([])
  const navigate = useNavigate()

  useEffect(() => {
    api.get(`/trips/${id}`).then(res => setTrip(res.data))
    api.get(`/trips/${id}/seats`).then(res => setSeats(res.data))
  }, [id])

  const toggle = sn => {
    setSelected(prev => prev.includes(sn) ? prev.filter(x=>x!==sn) : [...prev, sn])
  }

  const hold = async () => {
    const { data } = await api.post('/bookings/hold', { tripId: Number(id), seatNumbers: selected })
    navigate(`/checkout/${data.id}`)
  }

  if (!trip) return <div>Loading...</div>

  return (
    <div>
      <h5>Trip #{trip.id} — {trip.route?.source} → {trip.route?.destination}</h5>
      <div className="d-flex flex-wrap gap-2 mt-3">
        {seats.map(s => (
          <button key={s.id} disabled={s.booked}
            className={`btn ${selected.includes(s.seatNumber) ? 'btn-primary' : (s.booked ? 'btn-secondary' : 'btn-outline-primary')}`}
            onClick={()=>toggle(s.seatNumber)}>
            {s.seatNumber}
          </button>
        ))}
      </div>
      <div className="mt-3">
        <button className="btn btn-success" disabled={selected.length===0} onClick={hold}>
          Hold {selected.length} seat(s) • Pay ₹{(trip.fare||0)*selected.length}
        </button>
      </div>
    </div>
  )
}
