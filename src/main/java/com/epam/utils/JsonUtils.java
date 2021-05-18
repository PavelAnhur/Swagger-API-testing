package com.epam.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JsonUtils {
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
//        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {
    }

    public static String toJson(final Object src) {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}