package com.teoware.refapp.dao;
import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.CreateTaskInput;
import com.teoware.refapp.dao.dto.CreateTaskOutput;

@Local
public interface TaskDao extends Serializable {

	public CreateTaskOutput createTask(CreateTaskInput input);
}
