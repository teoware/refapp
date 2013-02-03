package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.task.TaskStatus;

public class UpdateTaskStatusInput extends ChangeInput {

	private TaskStatus taskStatus;

	public UpdateTaskStatusInput() {
		super();
	}

	public UpdateTaskStatusInput(Id id, TaskStatus taskStatus) {
		super(id);
		this.setTaskStatus(taskStatus);
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
}
