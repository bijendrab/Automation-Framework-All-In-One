package com.RestAPITesting.Test;



import com.restAPITesting.Listeners.TestListener;
import com.restAPITesting.api.api.AuthApi;
import com.restAPITesting.api.api.BookingApi;
import com.restAPITesting.api.api.PingApi;
import com.restAPITesting.api.payload.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Date;

import static com.restAPITesting.utils.Global.DEFAULT_PASSWORD;
import static com.restAPITesting.utils.Global.DEFAULT_USER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestListener.class)

public class ApiTest {

    @Test(groups = {"booking_test"})
    public void testHealthCheckReturns201() {
        Response response = PingApi.healthCheck();

        assertThat(response.getStatusCode() == 201).isTrue();
    }

    @Test(groups = {"booking_test"})
    public void testCreateTokenReturns200() {
        Auth auth = Auth.builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        Response response = AuthApi.createToken(auth);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test(groups = {"booking_test"})
    public void testGetBookingIdsReturns200() {
        Response response = BookingApi.getBookingIds();

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test(groups = {"booking_test"})
    public void testGetBookingReturns200() {
        Response response = BookingApi.getBooking(1, "application/json");

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test(groups = {"booking_test"})
    public void testGetBookingWithIncorrectAcceptReturns418() {
        Response response = BookingApi.getBooking(1, "test");

        assertThat(response.getStatusCode() == 418).isTrue();
    }

    @Test(groups = {"booking_test"})
    public void testCreateBookingReturns200() {
        BookingDates dates = BookingDates.builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = Booking.builder()
                .setFirstName("Mary")
                .setLastName("Active")
                .setTotalPrice(200)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        Response response = BookingApi.createBooking(payload);
        ResponseBody body = response.getBody();

        System.out.println("Response Body is: " + body.asString());
        assertThat(response.getStatusCode()).as(response.getStatusLine()).isEqualTo(200);

    }

    @Test(groups={"booking_test1"})
    public void testDeleteBookingReturns201() {
        BookingDates dates = BookingDates.builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = Booking.builder()
                .setFirstName("Test")
                .setLastName("Test")
                .setTotalPrice(100)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        BookingResponse bookingResponse = BookingApi.createBooking(payload).as(BookingResponse.class);

        Auth auth = Auth.builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        AuthResponse authResponse = AuthApi.createToken(auth).as(AuthResponse.class);

        Response response = BookingApi.deleteBooking(bookingResponse.getBookingId(), authResponse.getToken());

        assertThat(response.getStatusCode() == 201).isTrue();
    }
}

