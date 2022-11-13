package ru.netology.javacore.configuration;

import ru.netology.javacore.dao.OperationStore;
import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.logger.ConsoleLogger;
import ru.netology.javacore.logger.Logger;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.operation.AddOperationTask;
import ru.netology.javacore.operation.OperationTask;
import ru.netology.javacore.operation.RemoveOperationTask;
import ru.netology.javacore.operation.RestoreOperationTask;

import java.util.Map;

public class ConfigApplication {

    private final Todos todos;

    private final OperationStore operationStore;

    private Logger logger;

    private Map<OperationType, OperationTask> map;

    public ConfigApplication(Todos todos) {
        configLogger();
        this.todos = todos;
        this.operationStore = new OperationStore();
        configOperations(todos);
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

    public OperationStore getOperationStore() {
        return operationStore;
    }

    private void configOperations(Todos todos) {
        map = Map.of(
                OperationType.ADD, new AddOperationTask(this),
                OperationType.REMOVE, new RemoveOperationTask(this),
                OperationType.RESTORE, new RestoreOperationTask(this));
    }

    private void configLogger() {
        this.logger = new ConsoleLogger();
    }
}
