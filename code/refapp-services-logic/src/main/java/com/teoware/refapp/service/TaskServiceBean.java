package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.service.dto.ChangeTaskRequest;
import com.teoware.refapp.service.dto.ChangeTaskResponse;
import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;
import com.teoware.refapp.service.dto.DeleteTaskRequest;
import com.teoware.refapp.service.dto.DeleteTaskResponse;
import com.teoware.refapp.service.dto.FindTaskRequest;
import com.teoware.refapp.service.dto.FindTaskResponse;
import com.teoware.refapp.service.dto.ListTasksRequest;
import com.teoware.refapp.service.dto.ListTasksResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TaskServiceBean extends Service implements TaskService {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest request) {
		// TODO
		return null;
	}

	@Override
	public FindTaskResponse findTask(FindTaskRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListTasksResponse listTasks(ListTasksRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangeTaskResponse changeTask(ChangeTaskRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteTaskResponse deleteTask(DeleteTaskRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
