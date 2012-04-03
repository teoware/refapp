package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.TaskDaoLocal;
import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;

@Stateless(mappedName = "TaskDao")
public class TaskDaoImpl implements TaskDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
