package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.service.TaskServiceLocal;
import com.teoware.refapp.service.TaskServiceRemote;
import com.teoware.refapp.service.message.CreateTaskRequest;
import com.teoware.refapp.service.message.CreateTaskResponse;

@Stateless(mappedName="TaskService")
public class TaskServiceImpl implements TaskServiceLocal, TaskServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
		return null;
	}

}
