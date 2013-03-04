package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.teoware.refapp.service.TaskService;
import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TaskServiceFacadeBean implements TaskServiceFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private TaskService service;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest request) {
		return service.createTask(request);
	}
}
