package com.example.bus.repository;

import com.example.bus.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("select t from Trip t where t.route.source = :source and t.route.destination = :destination and t.departureTime between :start and :end")
    List<Trip> search(String source, String destination, Instant start, Instant end);
}
