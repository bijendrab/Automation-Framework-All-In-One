package com.restAPITesting.api.payload;

import org.codehaus.jackson.annotate.JsonProperty;

public class AuthResponse {
    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }
}
