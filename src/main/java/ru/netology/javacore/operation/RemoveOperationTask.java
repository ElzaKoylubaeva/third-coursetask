package ru.netology.javacore.operation;

import ru.netology.javacore.configuration.ConfigApplication;
import ru.netology.javacore.dao.OperationStore;
import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.model.Query;

public class RemoveOperationTask implements OperationTask {

    private ConfigApplication configApplication;

    public RemoveOperationTask(ConfigApplication configApplication) {
        this.configApplication = configApplication;
    }

    @Override
    public void operate(String task, boolean restore) {
        configApplication.getTodos().removeTask(task);
        if (!restore) {
            saveQuery(task);
        }
    }

    private void saveQuery(String task) {
        Query query = new Query();
        query.setType(OperationType.REMOVE);
        query.setTask(task);
        OperationStore operationStore = configApplication.getOperationStore();
        operationStore.addQuery(query);
    }
}
