package ru.netology.javacore.operation;

import ru.netology.javacore.configuration.ConfigApplication;
import ru.netology.javacore.dao.OperationStore;
import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.model.Query;

public class AddOperationTask implements OperationTask {

    private ConfigApplication configApplication;

    public AddOperationTask(ConfigApplication configApplication) {
        this.configApplication = configApplication;
    }

    @Override
    public void operate(String task, boolean restore) {
        configApplication.getTodos().addTask(task);
        if (!restore) {
            saveQuery(task);
        }
    }

    private void saveQuery(String task) {
        Query query = new Query();
        query.setType(OperationType.ADD);
        query.setTask(task);
        OperationStore operationStore = configApplication.getOperationStore();
        operationStore.addQuery(query);
    }
}
