package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TaskServiceBean implements TaskService {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
		return null;
	}

}
