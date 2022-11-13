package ru.netology.javacore.operation;

public interface OperationTask {

    default void operate(String task) {
        operate(task, false);
    }

    void operate(String task, boolean restore);
}
