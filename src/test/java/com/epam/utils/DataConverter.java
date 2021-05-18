package com.epam.utils;

import com.epam.data.Users;
import com.google.gson.Gson;

public final class DataConverter {

    public String convertObjectToJson(final Users users) {
        String userAsJson = new Gson().toJson(users);
        System.out.println("User as JSON file: " + userAsJson);
        return userAsJson;
    }
}
