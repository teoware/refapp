package com.teoware.refapp.service.facade;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;

@Local
public interface TaskServiceFacade extends Serializable {

	public CreateTaskResponse createTask(CreateTaskRequest request);
}
