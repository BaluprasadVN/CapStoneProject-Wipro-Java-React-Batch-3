package com.example.bus.controller;

import com.example.bus.dto.BookingDtos.*;
import com.example.bus.model.*;
import com.example.bus.repository.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final TripRepository tripRepo;
    private final SeatRepository seatRepo;
    private final BookingRepository bookingRepo;
    private final PaymentRepository paymentRepo;
    private final TicketRepository ticketRepo;

    public BookingController(TripRepository tripRepo, SeatRepository seatRepo, BookingRepository bookingRepo,
                             PaymentRepository paymentRepo, TicketRepository ticketRepo){
        this.tripRepo = tripRepo;
        this.seatRepo = seatRepo;
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;
        this.ticketRepo = ticketRepo;
    }

    @PostMapping("/bookings/hold")
    public Booking hold(@RequestBody HoldRequest req, @AuthenticationPrincipal com.example.bus.security.AppUserDetails user){
        Trip trip = tripRepo.findById(req.tripId).orElseThrow();
        // seat conflict check
        Set<Seat> selected = new HashSet<>();
        for (String sn : req.seatNumbers){
            Seat seat = seatRepo.findAll().stream()
                    .filter(s -> s.getTrip().getId().equals(trip.getId()) && s.getSeatNumber().equals(sn))
                    .findFirst().orElseThrow();
            if (seat.isBooked()) throw new RuntimeException("Seat conflict: " + sn);
            seat.setBooked(true); // lock immediately (simple approach)
            seatRepo.save(seat);
            selected.add(seat);
        }
        Booking b = new Booking();
        b.setTrip(trip);
        b.setUser(user.getUser());
        b.setStatus("HELD");
        b.setTotalAmount(trip.getFare() * selected.size());
        b.setSeats(selected);
        b.setBookingDate(Instant.now());
        bookingRepo.save(b);

        Payment p = new Payment();
        p.setBooking(b);
        p.setStatus("INITIATED");
        paymentRepo.save(p);
        return b;
    }

    @PostMapping("/payments/checkout")
    public Booking pay(@RequestBody PaymentRequest req){
        Booking b = bookingRepo.findById(req.bookingId).orElseThrow();
        Payment p = paymentRepo.findAll().stream().filter(x -> x.getBooking().getId().equals(b.getId())).findFirst().orElseThrow();
        // simulate success
        p.setStatus("SUCCESS");
        p.setGatewayRef("SIM-" + System.currentTimeMillis());
        paymentRepo.save(p);
        b.setStatus("CONFIRMED");
        bookingRepo.save(b);

        // Issue ticket with QR
        try {
            String contents = "TICKET:" + b.getId() + "|TRIP:" + b.getTrip().getId() + "|USER:" + b.getUser().getId();
            BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, 250, 250);
            Path dir = Path.of("tickets");
            Files.createDirectories(dir);
            Path file = dir.resolve("ticket_" + b.getId() + ".png");
            MatrixToImageWriter.writeToPath(matrix, "PNG", file);

            Ticket t = new Ticket();
            t.setBooking(b);
            t.setQrPath(file.toAbsolutePath().toString());
            ticketRepo.save(t);
        } catch(Exception e){
            // ignore
        }
        return b;
    }

    @PostMapping("/bookings/{id}/cancel")
    public Booking cancel(@PathVariable Long id){
        Booking b = bookingRepo.findById(id).orElseThrow();
        b.setStatus("CANCELLED");
        bookingRepo.save(b);
        // free seats
        for (Seat s : b.getSeats()){ s.setBooked(false); seatRepo.save(s); }
        return b;
    }

    @GetMapping("/tickets/{id}")
    public Ticket ticket(@PathVariable Long id){
        return ticketRepo.findById(id).orElseThrow();
    }
}
