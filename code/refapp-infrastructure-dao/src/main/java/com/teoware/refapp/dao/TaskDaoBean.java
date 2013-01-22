package com.teoware.refapp.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.teoware.refapp.dao.dto.CreateTaskDetailsInput;
import com.teoware.refapp.dao.dto.CreateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.CreateTaskInput;
import com.teoware.refapp.dao.dto.CreateTaskOutput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsInput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteTaskInput;
import com.teoware.refapp.dao.dto.DeleteTaskOutput;
import com.teoware.refapp.dao.dto.ReadTaskInput;
import com.teoware.refapp.dao.dto.ReadTaskOutput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateTaskInput;
import com.teoware.refapp.dao.dto.UpdateTaskOutput;
import com.teoware.refapp.dao.metadata.JNDI;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TaskDaoBean extends Dao implements TaskDao {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = JNDI.REFAPP_DATASOURCE)
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		super.initialize(dataSource);
	}

	@Override
	public CreateTaskOutput createTask(CreateTaskInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateTaskDetailsOutput createTaskDetails(CreateTaskDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadTaskOutput readTask(ReadTaskInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateTaskOutput updateTask(UpdateTaskInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateTaskDetailsOutput updateTaskDetails(UpdateTaskDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteTaskOutput deleteTask(DeleteTaskInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteTaskDetailsOutput deleteTaskDetails(DeleteTaskDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistConnection() {
		super.setPersistConnection(Boolean.TRUE);
	}

	@Override
	public void terminateConnection() {
		super.setPersistConnection(Boolean.FALSE);
		super.closeConnection();
	}
}
