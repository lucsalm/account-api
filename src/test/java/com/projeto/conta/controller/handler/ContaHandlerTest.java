package com.projeto.conta.controller.handler;

import com.projeto.conta.dto.response.error.ErrorResponseDTO;
import com.projeto.conta.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContaHandlerTest {

    @InjectMocks
    private ContaHandler contaHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void handlerNotFoundStatus() {
        ErrorResponseDTO errorResponse = contaHandler.handlerNotFoundStatusExceptions(new NotFoundException());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), errorResponse.message());
        assertEquals(HttpStatus.NOT_FOUND.name(), errorResponse.code());
    }


}