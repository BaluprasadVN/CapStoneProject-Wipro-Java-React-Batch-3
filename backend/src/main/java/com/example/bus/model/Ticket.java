package com.example.bus.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false)
    private Booking booking;

    @Column(nullable=false, unique=true)
    private String ticketNumber = UUID.randomUUID().toString();

    private String qrPath; // path to generated QR image
    private Instant issuedAt = Instant.now();

    public Ticket(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Booking getBooking(){ return booking; }
    public void setBooking(Booking booking){ this.booking = booking; }
    public String getTicketNumber(){ return ticketNumber; }
    public void setTicketNumber(String ticketNumber){ this.ticketNumber = ticketNumber; }
    public String getQrPath(){ return qrPath; }
    public void setQrPath(String qrPath){ this.qrPath = qrPath; }
    public Instant getIssuedAt(){ return issuedAt; }
    public void setIssuedAt(Instant issuedAt){ this.issuedAt = issuedAt; }
}
