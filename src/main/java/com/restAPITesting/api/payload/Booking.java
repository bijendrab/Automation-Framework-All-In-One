package com.restAPITesting.api.payload;

import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
public class Booking {
    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    Booking(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    public static BookingBuilder builder() {
        return new BookingBuilder();
    }


    public static class BookingBuilder {
        private String firstName;
        private String lastName;
        private int totalPrice;
        private boolean depositPaid;
        private BookingDates bookingDates;
        private String additionalNeeds;

        BookingBuilder() {
        }

        public BookingBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BookingBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BookingBuilder setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public BookingBuilder setDepositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public BookingBuilder setBookingDates(BookingDates bookingDates) {
            this.bookingDates = bookingDates;
            return this;
        }

        public BookingBuilder setAdditionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
            return this;
        }

        public Booking build() {
            return new Booking(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
        }

        public String toString() {
            return "Booking.BookingBuilder(firstName=" + this.firstName + ", lastName=" + this.lastName + ", totalPrice=" + this.totalPrice + ", depositPaid=" + this.depositPaid + ", bookingDates=" + this.bookingDates + ", additionalNeeds=" + this.additionalNeeds + ")";
        }
    }
}
