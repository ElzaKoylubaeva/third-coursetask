package ru.netology.javacore.operation;

import ru.netology.javacore.configuration.ConfigApplication;
import ru.netology.javacore.dao.OperationStore;
import ru.netology.javacore.logger.Logger;
import ru.netology.javacore.model.OperationType;
import ru.netology.javacore.model.Query;

public class RestoreOperationTask implements OperationTask {

    private final ConfigApplication configApplication;
    private final Logger logger;

    public RestoreOperationTask(ConfigApplication configApplication) {
        this.configApplication = configApplication;
        this.logger = configApplication.getLogger();
    }

    @Override
    public void operate(String task, boolean restore) {
        OperationStore operationStore = configApplication.getOperationStore();
        Query lastQuery = operationStore.getLastQuery();
        if (lastQuery != null) {
            logger.log("Revert operation: " + lastQuery);
            // revert operation type
            OperationType revertedType = revertOperation(lastQuery.getType());

            // do revert operation
            OperationTask operationTask = configApplication.getTask(revertedType);
            operationTask.operate(lastQuery.getTask(), true);
        } else {
            logger.log("No more operations");
        }
    }

    private OperationType revertOperation(OperationType type) {
        switch (type) {
            case ADD:
                return OperationType.REMOVE;
            case REMOVE:
                return OperationType.ADD;
            default:
                return null;
        }
    }
}
