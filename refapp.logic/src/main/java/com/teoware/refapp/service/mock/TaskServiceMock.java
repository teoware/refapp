package com.teoware.refapp.service.mock;

import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;
import com.teoware.refapp.service.TaskService;

public class TaskServiceMock implements TaskService {

	private static final long serialVersionUID = 1L;

	@Override
	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) {
		return null;
	}

}
