package com.projeto.conta.logs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.projeto.conta.ContaApplication;
import com.projeto.conta.logs.enums.TypeEnum;
import com.projeto.conta.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.IntegerRange;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.event.Level;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.math.NumberUtils.*;

@Slf4j
@Getter
@Component
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoggerStructured {

    private String requestId;
    private String endpoint;

    @JsonUnwrapped
    private LogStructured logStructured = new LogStructured();

    public LoggerStructured() {
        final ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            final HttpServletRequest request = attributes.getRequest();
            this.requestId = request.getRequestId();
            this.endpoint = request.getRequestURI();
        }
    }

    public void print() {
        print(Level.INFO);
    }

    public void print(Level level) {
        timestamp(LocalDateTime.now());
        final String logValue = JsonUtils.toJson(this);
        log.atLevel(level).log(logValue);
        clean();
    }

    private void clean() {
        this.logStructured = new LogStructured();
    }

    private void timestamp(LocalDateTime timestamp) {
        logStructured.setTimestamp(timestamp);
    }

    public LoggerStructured message(String message) {
        logStructured.setMessage(message);
        return this;
    }

    public LoggerStructured status(HttpStatus status) {
        logStructured.setStatus(status);
        return this;
    }

    public LoggerStructured type(TypeEnum type) {
        logStructured.setType(type);
        return this;
    }

    public LoggerStructured entity(Object entity) {
        logStructured.setEntity(entity);
        return this;
    }

    public LoggerStructured addCampo(String nome, Object valor) {
        Map<String, Object> campos = ObjectUtils
                .defaultIfNull(logStructured.getCampos(), new HashMap<>());
        campos.put(nome, valor);
        logStructured.setCampos(campos);
        return this;
    }

    public LoggerStructured stackTrace(StackTraceElement[] stackTrace) {
        String projectPackage = ContaApplication.class.getPackageName();

        List<String> stackTraceElements =  Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .toList();

        List<String> filteredStack = new ArrayList<>();
        boolean considerOutProjectStacks = Boolean.TRUE;
        for (String stackTraceElement : stackTraceElements) {
            boolean stackInProject = stackTraceElement.contains(projectPackage);
            if (considerOutProjectStacks || stackInProject) {
                filteredStack.add(stackTraceElement);
                if (stackInProject) {
                    considerOutProjectStacks = Boolean.FALSE;
                }
            }
        }

        logStructured.setStackTrace(filteredStack);

        return this;
    }

}
