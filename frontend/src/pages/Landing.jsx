import React from "react";
import { Link } from "react-router-dom";
import "./Landing.css";

export default function Landing() {
  return (
    <div className="landing d-flex flex-column justify-content-center align-items-center text-center">
      <div className="overlay"></div>
      <div className="content">
        <h1 className="display-4 fw-bold text-white">🚌 Bus Reservation System</h1>
        <p className="lead text-light">
          Book your journey with ease, comfort, and speed.
        </p>
        <Link to="/search" className="btn btn-lg btn-primary mt-3 shadow">
          🔍 Search Trips
        </Link>
      </div>
    </div>
  );
}
