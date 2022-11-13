package ru.netology.javacore.dao;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    public final static int MAX_NUMBER_TASK = 7;

    public final static String DELIMITER = " ";

    private final List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        if (tasks.size() < MAX_NUMBER_TASK) {
            tasks.add(task);
        }
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        return tasks.stream()
                .sorted()
                .collect(Collectors.joining(DELIMITER));
    }


    @Override
    public String toString() {
        return "Todos{" +
                "tasks=" + tasks +
                '}';
    }
}
