package com.restAPITesting.api.payload;

import lombok.Builder;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;
@Getter
@Builder(setterPrefix = "set")
public class Auth {
    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;


}
