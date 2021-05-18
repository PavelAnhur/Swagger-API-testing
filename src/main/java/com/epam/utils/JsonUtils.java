package com.epam.utils;

import com.google.gson.Gson;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static String toJson(final Object object) {
        String json = new Gson().toJson(object);
        System.out.println("Json: " + json);
        return json;
    }
}
