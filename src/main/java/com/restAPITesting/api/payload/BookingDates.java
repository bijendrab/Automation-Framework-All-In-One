package com.restAPITesting.api.payload;

import lombok.Builder;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
@Getter
@Builder(setterPrefix = "set")
public class BookingDates {
    @JsonProperty
    private Date checkin;

    @JsonProperty
    private Date checkout;


}
