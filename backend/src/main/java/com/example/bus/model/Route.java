package com.example.bus.model;

import jakarta.persistence.*;

@Entity
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String source;
    @Column(nullable=false)
    private String destination;
    private int distance; // km
    private int duration; // minutes

    public Route(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getSource(){ return source; }
    public void setSource(String source){ this.source = source; }
    public String getDestination(){ return destination; }
    public void setDestination(String destination){ this.destination = destination; }
    public int getDistance(){ return distance; }
    public void setDistance(int distance){ this.distance = distance; }
    public int getDuration(){ return duration; }
    public void setDuration(int duration){ this.duration = duration; }
}
