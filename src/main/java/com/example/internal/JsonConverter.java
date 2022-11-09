package com.example.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

public final class JsonConverter {

    private JsonConverter() {
    }

    @RequiredArgsConstructor
    public static abstract class Reading<T> implements Converter<String, T> {

        private final ObjectMapper objectMapper;
        private final Class<T> type;

        @Override
        public final T convert(String source) {
            try {
                return objectMapper.readValue(source, type);
            } catch (JsonProcessingException exception) {
                throw new RuntimeException("Error deserializing object to JSON", exception);
            }
        }
    }

    @RequiredArgsConstructor
    public static abstract class Writing<T> implements Converter<T, JdbcValue> {

        private final ObjectMapper objectMapper;

        @Override
        public final JdbcValue convert(T source) {
            try {
                final var json = objectMapper.writeValueAsString(source);

                return JdbcValue.of(json, JDBCType.BINARY);
            } catch (JsonProcessingException exception) {
                throw new RuntimeException("Error serializing object to JSON", exception);
            }
        }
    }
}
