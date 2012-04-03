package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;
import com.teoware.refapp.service.TaskServiceLocal;
import com.teoware.refapp.service.TaskServiceRemote;

@Stateless(name="TaskService", mappedName="/ejb/")
public class TaskServiceImpl implements TaskServiceLocal, TaskServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) {
		return null;
	}

}
