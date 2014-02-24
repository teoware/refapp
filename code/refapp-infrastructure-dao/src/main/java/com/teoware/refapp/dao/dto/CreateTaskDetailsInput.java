package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.task.TaskDetails;

public class CreateTaskDetailsInput extends ChangeInput {

    private TaskDetails taskDetails;

    public CreateTaskDetailsInput() {
        super();
    }

    public CreateTaskDetailsInput(Id id, TaskDetails taskDetails) {
        super(id);
        this.taskDetails = taskDetails;
    }

    public TaskDetails getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(TaskDetails taskDetails) {
        this.taskDetails = taskDetails;
    }
}
