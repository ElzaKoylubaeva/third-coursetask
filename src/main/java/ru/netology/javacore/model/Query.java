package ru.netology.javacore.model;

import ru.netology.javacore.model.OperationType;

public class Query {

    private OperationType type;

    private String task;

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Query{" +
                "type=" + type +
                ", task='" + task + '\'' +
                '}';
    }
}
