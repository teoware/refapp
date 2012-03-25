package com.teoware.refapp.dao;
import java.io.Serializable;

import com.teoware.refapp.dto.TaskCreateRequest;
import com.teoware.refapp.dto.TaskCreateResponse;

public interface TaskDao extends Serializable {

	public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest);
}
