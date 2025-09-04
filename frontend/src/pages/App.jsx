import React from "react";
import { Outlet, Link, useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

export default function App() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  let role = null;
  if (token) {
    try {
      role = jwtDecode(token).role;
    } catch {}
  }

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="d-flex flex-column min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark sticky-top px-3 shadow-sm">
        <Link className="navbar-brand fw-bold text-white" to="/">
          🚌 Bus Reservation
        </Link>
        <div className="ms-auto d-flex gap-2">
          <Link className="btn btn-outline-light" to="/search">
           Search
          </Link>
          {!token && (
            <Link className="btn btn-outline-primary" to="/login">
              Login
            </Link>
          )}
          {!token && (
            <Link className="btn btn-primary" to="/register">
              Register
            </Link>
          )}
          {token && (
            <button className="btn btn-danger" onClick={logout}>
              Logout
            </button>
          )}
        </div>
      </nav>

      {/* Page Content */}
      <main className="container flex-grow-1 py-4">
        <Outlet />
      </main>

      {/* Footer */}
      <footer className="text-center py-3 bg-light border-top mt-auto">
        <p className="mb-0">
          © 2025 Bus Reservation System | Made with ❤️
        </p>
      </footer>
    </div>
  );
}
