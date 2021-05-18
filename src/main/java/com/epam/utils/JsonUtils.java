package com.epam.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtils {
    private JsonUtils() {
    }

    public static String toJson(final Object object) {
        String json = new Gson().toJson(object);
        log.debug("Json:{}", json);
        return json;
    }
}
