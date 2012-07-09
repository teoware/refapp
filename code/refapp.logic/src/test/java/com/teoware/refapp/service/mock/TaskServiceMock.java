package com.teoware.refapp.service.mock;

import com.teoware.refapp.service.TaskServiceLocal;
import com.teoware.refapp.service.message.CreateTaskRequest;
import com.teoware.refapp.service.message.CreateTaskResponse;

public class TaskServiceMock implements TaskServiceLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest taskCreateRequest) {
		return null;
	}

}
