package com.senecacollege.assignment4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    public static List<User> parseJson(String jsonString) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>(){}.getType();
        return gson.fromJson(jsonString, userListType);
    }
}
