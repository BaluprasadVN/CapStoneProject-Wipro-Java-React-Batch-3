/*import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function Login(){
  const [email, setEmail] = useState('user@bus.com')
  const [password, setPassword] = useState('user123')
  const [error, setError] = useState(null)
  const navigate = useNavigate()

  const submit = async e => {
    e.preventDefault()
    try {
      const { data } = await api.post('/auth/login', { email, password })
      localStorage.setItem('token', data.accessToken)
      localStorage.setItem('user', JSON.stringify(data))
      navigate('/search')
    } catch (err) {
      setError('Login failed')
    }
  }

  return (
    <form className="col-12 col-md-6 mx-auto" onSubmit={submit}>
      <h4>Login</h4>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="mb-2">
        <label>Email</label>
        <input className="form-control" value={email} onChange={e=>setEmail(e.target.value)} />
      </div>
      <div className="mb-2">
        <label>Password</label>
        <input type="password" className="form-control" value={password} onChange={e=>setPassword(e.target.value)} />
      </div>
      <button className="btn btn-primary">Login</button>
    </form>
  )
}
*/
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errors, setErrors] = useState({})
  const [serverError, setServerError] = useState(null)
  const navigate = useNavigate()

  const validate = () => {
    const newErrors = {}
    if (!email.trim()) newErrors.email = 'Email is required'
    else if (!/\S+@\S+\.\S+/.test(email)) newErrors.email = 'Invalid email format'
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
      const { data } = await api.post('/auth/login', { email, password })
      localStorage.setItem('token', data.accessToken)
      localStorage.setItem('user', JSON.stringify(data))
      navigate('/search')
    } catch (err) {
      setServerError('Login failed')
    }
  }

  return (
    <div className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <div className="card shadow-lg p-4" style={{ maxWidth: '400px', width: '100%' }}>
        <h3 className="text-center mb-3">Login</h3>
        {serverError && <div className="alert alert-danger">{serverError}</div>}
        <form onSubmit={submit} noValidate>
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
            <label>Password</label>
            <input
              type="password"
              className={`form-control ${errors.password ? 'is-invalid' : ''}`}
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
            <div className="invalid-feedback">{errors.password}</div>
          </div>
          <button className="btn btn-primary w-100">Login</button>
        </form>
      </div>
    </div>
  )
}
