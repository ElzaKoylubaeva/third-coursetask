package ru.netology.javacore.operation;

import ru.netology.javacore.dao.Todos;

public class AddOperationTask implements OperationTask {

    private Todos todos;

    public AddOperationTask(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void operate(String task) {
        todos.addTask(task);
    }
}
