package com.projeto.conta.controller.handler;

import com.projeto.conta.dto.response.error.ErrorResponseDTO;
import com.projeto.conta.exception.NotFoundException;
import com.projeto.conta.exception.UnprocessableEntityException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ContaHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ValidationException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class, HttpMessageConversionException.class, UnprocessableEntityException.class})
    public ErrorResponseDTO handlerUnprocessableEntityStatusExceptions(final Exception exception) {
        return new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    public ErrorResponseDTO handlerNotFoundStatusExceptions(final Exception exception) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handlerAnyOtherException(final Exception exception) {
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
