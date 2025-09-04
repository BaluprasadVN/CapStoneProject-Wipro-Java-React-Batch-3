package com.example.bus.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false)
    private Booking booking;

    private String status; // INITIATED, SUCCESS, FAILED, REFUNDED
    private String gatewayRef;
    private Instant createdAt = Instant.now();

    public Payment(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Booking getBooking(){ return booking; }
    public void setBooking(Booking booking){ this.booking = booking; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
    public String getGatewayRef(){ return gatewayRef; }
    public void setGatewayRef(String gatewayRef){ this.gatewayRef = gatewayRef; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
}
