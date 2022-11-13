package ru.netology.javacore.operation;

import ru.netology.javacore.dao.Todos;

public class RemoveOperationTask implements OperationTask {

    private Todos todos;

    public RemoveOperationTask(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void operate(String task) {
        todos.removeTask(task);
    }
}
