package com.example.bus.controller;

import com.example.bus.model.Bus;
import com.example.bus.model.Route;
import com.example.bus.model.Trip;
import com.example.bus.repository.BusRepository;
import com.example.bus.repository.RouteRepository;
import com.example.bus.repository.TripRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    private final BusRepository busRepo;
    private final RouteRepository routeRepo;
    private final TripRepository tripRepo;

    public AdminController(BusRepository busRepo, RouteRepository routeRepo, TripRepository tripRepo){
        this.busRepo = busRepo;
        this.routeRepo = routeRepo;
        this.tripRepo = tripRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/buses")
    public Bus createBus(@RequestBody Bus bus){ return busRepo.save(bus); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/routes")
    public Route createRoute(@RequestBody Route route){ return routeRepo.save(route); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/trips")
    public Trip createTrip(@RequestBody Trip trip){ return tripRepo.save(trip); }

    @GetMapping("/trips/search")
    public List<Trip> searchTrips(@RequestParam String source, @RequestParam String destination, @RequestParam String dateIso){
        Instant start = Instant.parse(dateIso + "T00:00:00Z");
        Instant end = Instant.parse(dateIso + "T23:59:59Z");
        return tripRepo.search(source,destination,start,end);
    }
}
