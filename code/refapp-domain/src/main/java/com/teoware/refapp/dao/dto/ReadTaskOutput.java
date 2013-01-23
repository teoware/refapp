package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.task.Task;

public class ReadTaskOutput {

	List<Task> taskList;

	public ReadTaskOutput(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<Task> getTaskList() {
		return taskList;
	}
}
