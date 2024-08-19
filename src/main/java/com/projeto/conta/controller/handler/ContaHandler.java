package com.projeto.conta.controller.handler;

import com.projeto.conta.dto.response.error.ErrorResponseDTO;
import com.projeto.conta.exception.NotFoundException;
import com.projeto.conta.exception.UnprocessableEntityException;
import com.projeto.conta.logs.LoggerStructured;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.projeto.conta.logs.enums.TypeEnum.RESPONSE;
import static org.slf4j.event.Level.ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class ContaHandler {

    private final LoggerStructured log;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ValidationException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class, HttpMessageConversionException.class, UnprocessableEntityException.class})
    public ErrorResponseDTO handlerUnprocessableEntityStatusExceptions(final Exception exception) {

        final ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY);
        logError(errorResponseDTO,exception);

        return errorResponseDTO;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    public ErrorResponseDTO handlerNotFoundStatusExceptions(final Exception exception) {

        final ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND);
        logError(errorResponseDTO,exception);

        return errorResponseDTO;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handlerAnyOtherException(final Exception exception) {
        final ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR);
        logError(errorResponseDTO,exception);

        return errorResponseDTO;
    }

    private void logError(ErrorResponseDTO errorResponseDTO, Exception exception) {
        log.type(RESPONSE)
                .entity(errorResponseDTO)
                .stackTrace(exception.getStackTrace())
                .status(errorResponseDTO.httpStatus())
                .print(ERROR);
    }

}
