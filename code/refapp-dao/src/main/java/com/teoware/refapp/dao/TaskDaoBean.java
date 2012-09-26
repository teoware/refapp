package com.teoware.refapp.dao;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.dto.InsertTaskRequest;
import com.teoware.refapp.dao.dto.InsertTaskResponse;

@Stateless(mappedName = "TaskDao")
public class TaskDaoBean implements TaskDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertTaskResponse insertTask(InsertTaskRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
