package com.challengeliteratura.challengeliteratura.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConverteDados implements IConverteDados {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
