package com.example.bus.model;

import jakarta.persistence.*;

@Entity
public class Bus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String busNumber;
    private String busType; // AC/Non-AC/Sleeper
    private int totalSeats;
    private String operatorName;

    public Bus(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getBusNumber(){ return busNumber; }
    public void setBusNumber(String busNumber){ this.busNumber = busNumber; }
    public String getBusType(){ return busType; }
    public void setBusType(String busType){ this.busType = busType; }
    public int getTotalSeats(){ return totalSeats; }
    public void setTotalSeats(int totalSeats){ this.totalSeats = totalSeats; }
    public String getOperatorName(){ return operatorName; }
    public void setOperatorName(String operatorName){ this.operatorName = operatorName; }
}
