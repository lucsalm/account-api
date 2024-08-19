package com.projeto.conta.logs;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.conta.logs.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LogStructured {

    private LocalDateTime timestamp;
    private String message;
    private Object entity;
    private Map<String, Object> campos;
    private TypeEnum type;
    private HttpStatus status;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> stackTrace;

}

