package com.projeto.conta.logs;


import com.projeto.conta.logs.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class LogStructured {

    private LocalDateTime timestamp;
    private String message;
    private Object entity;
    private Map<String, Object> campos;
    private TypeEnum type;
    private HttpStatus status;

}

