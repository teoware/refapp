package com.teoware.refapp.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;
import com.teoware.refapp.service.TaskService;

@Stateless(mappedName = "TaskService")
@Remote(value = TaskService.class)
public class TaskServiceImpl implements TaskService {

	private static final long serialVersionUID = 1L;

	@Override
	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) {
		return null;
	}

}
