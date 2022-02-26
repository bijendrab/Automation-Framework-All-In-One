package com.restAPITesting.api.payload;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingResponse {
    @JsonProperty("bookingid")
    private int bookingId;

    @JsonProperty
    private Booking booking;

    public int getBookingId() {
        return bookingId;
    }

    public Booking getBooking() {
        return booking;
    }
}
