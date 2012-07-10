package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.TaskDaoLocal;
import com.teoware.refapp.dao.message.InsertTaskRequest;
import com.teoware.refapp.dao.message.InsertTaskResponse;

@Stateless(mappedName = "TaskDao")
public class TaskDaoImpl implements TaskDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertTaskResponse insertTask(InsertTaskRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
