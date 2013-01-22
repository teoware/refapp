package com.teoware.refapp.model.task;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.common.Title;

public class Task {

	@NotNull
	@Valid
	private Title title;

	@NotNull
	@Valid
	private TaskDetails taskDetails;

	private TaskStatus taskStatus;

	public Task() {
	}

	public Task(Title title, TaskDetails taskDetails, TaskStatus taskStatus) {
		this.title = title;
		this.taskDetails = taskDetails;
		this.taskStatus = taskStatus;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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
