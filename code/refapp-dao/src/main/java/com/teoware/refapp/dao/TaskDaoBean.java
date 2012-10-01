package com.teoware.refapp.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.dao.dto.InsertTaskRequest;
import com.teoware.refapp.dao.dto.InsertTaskResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TaskDaoBean implements TaskDao {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertTaskResponse insertTask(InsertTaskRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
