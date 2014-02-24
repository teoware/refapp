package com.teoware.refapp.model.task;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.common.Uuid;

public class Task {

    @NotNull
    @Valid
    private Uuid uuid;

    @NotNull
    @Valid
    private TaskDetails taskDetails;

    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(Uuid uuid, TaskDetails taskDetails, TaskStatus taskStatus) {
        this.uuid = uuid;
        this.taskDetails = taskDetails;
        this.taskStatus = taskStatus;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }

    public TaskDetails getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(TaskDetails taskDetails) {
        this.taskDetails = taskDetails;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
