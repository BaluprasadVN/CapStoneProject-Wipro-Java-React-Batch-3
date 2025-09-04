package com.example.bus.dto;

import java.util.List;

public class BookingDtos {
    public static class HoldRequest {
        public Long tripId;
        public List<String> seatNumbers;
    }
    public static class PaymentRequest {
        public Long bookingId;
    }
}
