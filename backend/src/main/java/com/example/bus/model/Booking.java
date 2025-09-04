package com.example.bus.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;
    @ManyToOne(optional=false)
    private Trip trip;

    private Instant bookingDate = Instant.now();
    private double totalAmount;
    private String status; // HELD, CONFIRMED, CANCELLED

    @ManyToMany
    @JoinTable(name="booking_seats", joinColumns=@JoinColumn(name="booking_id"), inverseJoinColumns=@JoinColumn(name="seat_id"))
    private Set<Seat> seats = new HashSet<>();

    public Booking(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }
    public Trip getTrip(){ return trip; }
    public void setTrip(Trip trip){ this.trip = trip; }
    public Instant getBookingDate(){ return bookingDate; }
    public void setBookingDate(Instant bookingDate){ this.bookingDate = bookingDate; }
    public double getTotalAmount(){ return totalAmount; }
    public void setTotalAmount(double totalAmount){ this.totalAmount = totalAmount; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
    public Set<Seat> getSeats(){ return seats; }
    public void setSeats(Set<Seat> seats){ this.seats = seats; }
}
