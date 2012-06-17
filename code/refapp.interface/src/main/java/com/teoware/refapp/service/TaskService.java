package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;

public interface TaskService extends Serializable {

	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest);
}
