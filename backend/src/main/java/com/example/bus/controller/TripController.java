package com.example.bus.controller;

import com.example.bus.model.Seat;
import com.example.bus.model.Trip;
import com.example.bus.repository.SeatRepository;
import com.example.bus.repository.TripRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {
    private final TripRepository tripRepo;
    private final SeatRepository seatRepo;

    public TripController(TripRepository tripRepo, SeatRepository seatRepo){
        this.tripRepo = tripRepo;
        this.seatRepo = seatRepo;
    }

    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable Long id){ return tripRepo.findById(id).orElseThrow(); }

    @GetMapping("/{id}/seats")
    public List<Seat> seats(@PathVariable Long id){
        return seatRepo.findAll().stream().filter(s -> s.getTrip().getId().equals(id)).toList();
    }
}
