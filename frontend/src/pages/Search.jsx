import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api'

export default function Search(){
  const [source, setSource] = useState('Pune')
  const [destination, setDestination] = useState('Mumbai')
  const [dateIso, setDateIso] = useState(new Date(Date.now()+86400000).toISOString().slice(0,10))
  const [trips, setTrips] = useState([])

  const run = async e => {
    e.preventDefault()
    const { data } = await api.get('/trips/search', { params: { source, destination, dateIso } })
    setTrips(data)
  }

  return (
    <div>
      <form className="row g-2 align-items-end" onSubmit={run}>
        <div className="col-md-3">
          <label>Source</label>
          <input className="form-control" value={source} onChange={e=>setSource(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label>Destination</label>
          <input className="form-control" value={destination} onChange={e=>setDestination(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label>Date (YYYY-MM-DD)</label>
          <input className="form-control" value={dateIso} onChange={e=>setDateIso(e.target.value)} />
        </div>
        <div className="col-md-3">
          <button className="btn btn-primary w-100">Search</button>
        </div>
      </form>

      <div className="mt-3">
        {trips.map(t => (
          <div className="card mb-2" key={t.id}>
            <div className="card-body d-flex justify-content-between align-items-center">
              <div>
                <b>{t.route?.source} → {t.route?.destination}</b><br/>
                Bus: {t.bus?.operatorName} ({t.bus?.busType}) | Fare: ₹{t.fare}
              </div>
              <Link className="btn btn-success" to={`/trip/${t.id}`}>Select Seats</Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
