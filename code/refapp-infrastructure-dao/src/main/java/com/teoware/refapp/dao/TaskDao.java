package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.CreateTaskDetailsInput;
import com.teoware.refapp.dao.dto.CreateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.CreateTaskInput;
import com.teoware.refapp.dao.dto.CreateTaskOutput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsInput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteTaskInput;
import com.teoware.refapp.dao.dto.DeleteTaskOutput;
import com.teoware.refapp.dao.dto.DeleteTaskStatusInput;
import com.teoware.refapp.dao.dto.DeleteTaskStatusOutput;
import com.teoware.refapp.dao.dto.ReadTaskInput;
import com.teoware.refapp.dao.dto.ReadTaskOutput;
import com.teoware.refapp.dao.dto.ReadTasksInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateTaskInput;
import com.teoware.refapp.dao.dto.UpdateTaskOutput;
import com.teoware.refapp.dao.dto.UpdateTaskStatusInput;
import com.teoware.refapp.dao.dto.UpdateTaskStatusOutput;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Uuid;

@Local
public interface TaskDao extends Serializable {

	public CreateTaskOutput createTask(CreateTaskInput input) throws DaoException;

	public CreateTaskDetailsOutput createTaskDetails(CreateTaskDetailsInput input) throws DaoException;

	public Id readTaskId(Uuid uuid) throws DaoException;

	public ReadTaskOutput readTask(ReadTaskInput input) throws DaoException;

	public ReadTaskOutput readTasks(ReadTasksInput input) throws DaoException;

	public UpdateTaskOutput updateTask(UpdateTaskInput input) throws DaoException;

	public UpdateTaskDetailsOutput updateTaskDetails(UpdateTaskDetailsInput input) throws DaoException;

	public UpdateTaskStatusOutput updateTaskStatus(UpdateTaskStatusInput input) throws DaoException;

	public DeleteTaskOutput deleteTask(DeleteTaskInput input) throws DaoException;

	public DeleteTaskDetailsOutput deleteTaskDetails(DeleteTaskDetailsInput input) throws DaoException;

	public DeleteTaskStatusOutput deleteTaskStatus(DeleteTaskStatusInput input) throws DaoException;

	public void persistConnection();

	public void terminateConnection();
}
