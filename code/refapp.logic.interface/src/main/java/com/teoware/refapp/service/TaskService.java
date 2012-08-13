package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;

public interface TaskService extends Serializable {

	public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest);
}
