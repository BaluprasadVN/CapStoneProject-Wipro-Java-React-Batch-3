/*import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function Register(){
  const [name, setName] = useState('New User')
  const [email, setEmail] = useState('new@bus.com')
  const [phone, setPhone] = useState('7777777777')
  const [password, setPassword] = useState('pass123')
  const [error, setError] = useState(null)
  const navigate = useNavigate()

  const submit = async e => {
    e.preventDefault()
    try {
      const { data } = await api.post('/auth/register', { name, email, phone, password })
      localStorage.setItem('token', data.accessToken)
      localStorage.setItem('user', JSON.stringify(data))
      navigate('/search')
    } catch (err) {
      setError('Registration failed')
    }
  }

  return (
    <form className="col-12 col-md-6 mx-auto" onSubmit={submit}>
      <h4>Register</h4>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="mb-2">
        <label>Name</label>
        <input className="form-control" value={name} onChange={e=>setName(e.target.value)} />
      </div>
      <div className="mb-2">
        <label>Email</label>
        <input className="form-control" value={email} onChange={e=>setEmail(e.target.value)} />
      </div>
      <div className="mb-2">
        <label>Phone</label>
        <input className="form-control" value={phone} onChange={e=>setPhone(e.target.value)} />
      </div>
      <div className="mb-2">
        <label>Password</label>
        <input type="password" className="form-control" value={password} onChange={e=>setPassword(e.target.value)} />
      </div>
      <button className="btn btn-primary">Create Account</button>
    </form>
  )
}*/
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function Register() {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [phone, setPhone] = useState('')
  const [password, setPassword] = useState('')
  const [errors, setErrors] = useState({})
  const [serverError, setServerError] = useState(null)
  const navigate = useNavigate()

  const validate = () => {
    const newErrors = {}
    if (!name.trim()) newErrors.name = 'Name is required'
    if (!email.trim()) newErrors.email = 'Email is required'
    else if (!/\S+@\S+\.\S+/.test(email)) newErrors.email = 'Invalid email format'
    if (!phone.trim()) newErrors.phone = 'Phone is required'
    else if (!/^\d{10}$/.test(phone)) newErrors.phone = 'Phone must be 10 digits'
    if (!password.trim()) newErrors.password = 'Password is required'
    else if (password.length < 6) newErrors.password = 'At least 6 characters'
    return newErrors
  }

  const submit = async e => {
    e.preventDefault()
    const newErrors = validate()
    setErrors(newErrors)
    if (Object.keys(newErrors).length > 0) return

    try {
      const { data } = await api.post('/auth/register', { name, email, phone, password })
      localStorage.setItem('token', data.accessToken)
      localStorage.setItem('user', JSON.stringify(data))
      navigate('/search')
    } catch (err) {
      setServerError('Registration failed')
    }
  }

  return (
    <div className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <div className="card shadow-lg p-4" style={{ maxWidth: '500px', width: '100%' }}>
        <h3 className="text-center mb-3">Create Account</h3>
        {serverError && <div className="alert alert-danger">{serverError}</div>}
        <form onSubmit={submit} noValidate>
          <div className="mb-3">
            <label>Name</label>
            <input
              className={`form-control ${errors.name ? 'is-invalid' : ''}`}
              value={name}
              onChange={e => setName(e.target.value)}
            />
            <div className="invalid-feedback">{errors.name}</div>
          </div>
          <div className="mb-3">
            <label>Email</label>
            <input
              type="email"
              className={`form-control ${errors.email ? 'is-invalid' : ''}`}
              value={email}
              onChange={e => setEmail(e.target.value)}
            />
            <div className="invalid-feedback">{errors.email}</div>
          </div>
          <div className="mb-3">
            <label>Phone</label>
            <input
              type="tel"
              className={`form-control ${errors.phone ? 'is-invalid' : ''}`}
              value={phone}
              onChange={e => setPhone(e.target.value)}
            />
            <div className="invalid-feedback">{errors.phone}</div>
          </div>
          <div className="mb-3">
            <label>Password</label>
            <input
              type="password"
              className={`form-control ${errors.password ? 'is-invalid' : ''}`}
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
            <div className="invalid-feedback">{errors.password}</div>
          </div>
          <button className="btn btn-success w-100">Register</button>
        </form>
      </div>
    </div>
  )
}

