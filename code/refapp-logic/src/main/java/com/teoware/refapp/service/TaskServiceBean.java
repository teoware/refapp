package com.teoware.refapp.service;

import javax.ejb.Stateless;

import com.teoware.refapp.service.TaskServiceLocal;
import com.teoware.refapp.service.TaskServiceRemote;
import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;

@Stateless(mappedName="TaskService")
public class TaskServiceBean implements TaskServiceLocal, TaskServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
		return null;
	}

}
