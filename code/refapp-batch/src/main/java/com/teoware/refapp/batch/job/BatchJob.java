package com.teoware.refapp.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.domain.TaskResult;
import com.teoware.refapp.batch.domain.TaskSetup;
import com.teoware.refapp.batch.task.BatchTask;
import com.teoware.refapp.batch.task.TaskTerminatedException;
import com.teoware.refapp.batch.util.BatchTaskHandler;

public abstract class BatchJob {

    private static final Logger LOG = LoggerFactory.getLogger(BatchJob.class);

    private BatchTaskHandler taskHandler = new BatchTaskHandler();

    public String name() {
        return this.getClass().getSimpleName();
    }

    protected abstract void setup();

    protected void addTask(BatchTask<?> task) {
        taskHandler.add(task);
        LOG.info("Added new task {} to batch job {}", task.name(), name());
    }

    public void run() {
        if (taskHandler.count() > 0) {
            runTasks();
        } else {
            throw new BatchException("Task list empty. No batch tasks will run");
        }
    }

    private void runTasks() {
        TaskResult result = null;
        for (BatchTask<?> task : taskHandler.list()) {
            try {
                result = runTask(task, result);
            } catch (TaskTerminatedException e) {
                LOG.warn("{}. Batch job {} will now exit", e.getMessage(), name());
                return;
            }
        }
    }

    protected TaskResult runTask(BatchTask<?> task, TaskResult result) {
        if (task != null) {
            LOG.info("Batch job {} running task {}", name(), task.name());
            TaskSetup setup = processSetup(task, result);
            result = task.run(setup);
            return processResult(task, result);
        } else {
            throw new BatchException("Unable to run batch task which is null");
        }
    }

    private TaskSetup processSetup(BatchTask<?> task, TaskResult result) {
        if (result != null) {
            return task.setup(result.data());
        } else {
            return task.setup(null);
        }
    }

    protected TaskResult processResult(BatchTask<?> task, TaskResult result) {
        if (result != null && result.terminate()) {
            String message = String.format("Task %s initiated terminate action", task.name());
            throw new TaskTerminatedException(message);
        } else {
            return result;
        }
    }
}
