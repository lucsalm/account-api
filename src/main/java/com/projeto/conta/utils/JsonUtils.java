package com.projeto.conta.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();


    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return Strings.EMPTY;
        }
    }
}
