package ru.netology.javacore.parser;

import com.google.gson.Gson;
import ru.netology.javacore.model.Query;

public class GsonParser {

    private static final GsonParser instance = new GsonParser();

    private final Gson gson = new Gson();

    private GsonParser() {}

    public static GsonParser getInstance() {
        return instance;
    }

    public Query parseJsonToQuery(String json) {
        return gson.fromJson(json, Query.class);
    }
}
