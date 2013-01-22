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
import com.teoware.refapp.dao.dto.ReadTaskInput;
import com.teoware.refapp.dao.dto.ReadTaskOutput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateTaskInput;
import com.teoware.refapp.dao.dto.UpdateTaskOutput;

@Local
public interface TaskDao extends Serializable {

	public CreateTaskOutput createTask(CreateTaskInput input);

	public CreateTaskDetailsOutput createTaskDetails(CreateTaskDetailsInput input);
	
	public ReadTaskOutput readTask(ReadTaskInput input);

	public UpdateTaskOutput updateTask(UpdateTaskInput input);

	public UpdateTaskDetailsOutput updateTaskDetails(UpdateTaskDetailsInput input);

	public DeleteTaskOutput deleteTask(DeleteTaskInput input);

	public DeleteTaskDetailsOutput deleteTaskDetails(DeleteTaskDetailsInput input);

	public void persistConnection();

	public void terminateConnection();
}
