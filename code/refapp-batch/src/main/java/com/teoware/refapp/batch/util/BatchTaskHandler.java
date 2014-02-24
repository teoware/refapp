package com.teoware.refapp.batch.util;

import java.util.LinkedList;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.task.BatchTask;

public class BatchTaskHandler {

    private LinkedList<BatchTask> tasks = new LinkedList<BatchTask>();

    public int count() {
        return tasks.size();
    }

    public LinkedList<BatchTask> list() {
        return tasks;
    }

    public void add(BatchTask task) {
        if (task != null) {
            tasks.add(task);
        } else {
            throw new BatchException("Unable to add a batch task which is null");
        }
    }

    public void remove(BatchTask task) {
        if (task != null) {
            tasks.remove(task);
        } else {
            throw new BatchException("Unable to remove a batch task which is null");
        }
    }
}
