package ru.netology.javacore.service;

import ru.netology.javacore.configuration.ConfigApplication;
import ru.netology.javacore.dao.OperationStore;
import ru.netology.javacore.logger.Logger;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.model.Query;
import ru.netology.javacore.operation.OperationTask;

public class TaskService {

    private final ConfigApplication configApplication;

    private final Logger logger;

    public TaskService(ConfigApplication configApplication) {
        this.configApplication = configApplication;
        this.logger = configApplication.getLogger();
    }

    public void runTask(Query query) {
        OperationType operationType = query.getType();
        OperationTask task = configApplication.getTask(operationType);
        logger.log("start operation: " + operationType + " with data: " + query.getTask());
        task.operate(query.getTask());
    }

    public String getTaskResult() {
        return configApplication.getTodos().getAllTasks();
    }
}
