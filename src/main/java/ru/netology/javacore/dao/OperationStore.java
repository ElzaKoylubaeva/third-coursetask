package ru.netology.javacore.dao;

import ru.netology.javacore.model.Query;

import java.util.Deque;
import java.util.LinkedList;

public class OperationStore {

    private final Deque<Query> data = new LinkedList<>();

    public void addQuery(Query query) {
        data.push(query);
    }

    public Query getLastQuery() {
        return data.pop();
    }
}
