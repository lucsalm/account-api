package com.projeto.conta.dto.response.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(@JsonIgnore HttpStatus httpStatus) {
    @JsonProperty("message")
    public String message() {
        return httpStatus.getReasonPhrase();
    }

    @JsonProperty("code")
    public String code() {
        return httpStatus.name();
    }
}