package com.teoware.refapp.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.TaskDao;
import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;

@Stateless(mappedName = "TaskDao")
@Remote(value = TaskDao.class)
public class TaskDaoImpl implements TaskDao {

	private static final long serialVersionUID = 1L;

	@Override
	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
