package com.epam.data;

import com.google.gson.Gson;

public class DataConverter {

    private DataConverter() {
    }

    public static String objectToJsonConvert(User user) {
        return new Gson().toJson(user);
    }
}
