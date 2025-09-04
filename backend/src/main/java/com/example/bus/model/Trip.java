package com.example.bus.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Trip {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Bus bus;

    @ManyToOne(optional=false)
    private Route route;

    private Instant departureTime;
    private Instant arrivalTime;
    private double fare;

    public Trip(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Bus getBus(){ return bus; }
    public void setBus(Bus bus){ this.bus = bus; }
    public Route getRoute(){ return route; }
    public void setRoute(Route route){ this.route = route; }
    public Instant getDepartureTime(){ return departureTime; }
    public void setDepartureTime(Instant departureTime){ this.departureTime = departureTime; }
    public Instant getArrivalTime(){ return arrivalTime; }
    public void setArrivalTime(Instant arrivalTime){ this.arrivalTime = arrivalTime; }
    public double getFare(){ return fare; }
    public void setFare(double fare){ this.fare = fare; }
}
