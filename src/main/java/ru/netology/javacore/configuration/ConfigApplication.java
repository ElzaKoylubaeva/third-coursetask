package ru.netology.javacore.configuration;

import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.logger.ConsoleLogger;
import ru.netology.javacore.logger.Logger;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.operation.AddOperationTask;
import ru.netology.javacore.operation.OperationTask;
import ru.netology.javacore.operation.RemoveOperationTask;

import java.util.Map;

public class ConfigApplication {

    private final Todos todos;

    private Logger logger;

    private Map<OperationType, OperationTask> map;

    public ConfigApplication(Todos todos) {
        this.todos = todos;
        configOperations(todos);
        configLogger();
    }

    public OperationTask getTask(OperationType operationType) {
        return map.get(operationType);
    }

    public Todos getTodos() {
        return todos;
    }

    public Logger getLogger() {
        return logger;
    }

    private void configOperations(Todos todos) {
        map = Map.of(
                OperationType.ADD, new AddOperationTask(todos),
                OperationType.REMOVE, new RemoveOperationTask(todos));
    }

    private void configLogger() {
        this.logger = new ConsoleLogger();
    }
}
