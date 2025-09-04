package com.example.bus.config;

import com.example.bus.model.*;
import com.example.bus.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner seed(UserRepository users, PasswordEncoder encoder,
                           BusRepository buses, RouteRepository routes, TripRepository trips, SeatRepository seats){
        return args -> {
            if (users.count() == 0){
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@bus.com");
                admin.setPhone("9999999999");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                users.save(admin);

                User c = new User();
                c.setName("Test User");
                c.setEmail("user@bus.com");
                c.setPhone("8888888888");
                c.setPassword(encoder.encode("user123"));
                c.setRole(Role.CUSTOMER);
                users.save(c);

                Bus bus = new Bus();
                bus.setBusNumber("MH12-AB-1234");
                bus.setBusType("AC");
                bus.setTotalSeats(40);
                bus.setOperatorName("Express Travels");
                buses.save(bus);

                Route r = new Route();
                r.setSource("Pune");
                r.setDestination("Mumbai");
                r.setDistance(150);
                r.setDuration(210);
                routes.save(r);

                Trip t = new Trip();
                t.setBus(bus);
                t.setRoute(r);
                t.setFare(500);
                t.setDepartureTime(Instant.now().plus(1, ChronoUnit.DAYS));
                t.setArrivalTime(Instant.now().plus(1, ChronoUnit.DAYS).plus(4, ChronoUnit.HOURS));
                trips.save(t);

                List<Seat> seatList = new ArrayList<>();
                for (int i=1;i<=bus.getTotalSeats();i++){
                    Seat s = new Seat();
                    s.setTrip(t);
                    s.setSeatNumber("S"+i);
                    s.setSeatType(i%4==1||i%4==0 ? "window" : "aisle");
                    seatList.add(s);
                }
                seats.saveAll(seatList);
            }
        };
    }
}
