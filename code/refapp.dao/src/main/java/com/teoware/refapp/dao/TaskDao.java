package com.teoware.refapp.dao;
import java.io.Serializable;

import com.teoware.refapp.dao.dto.InsertTaskRequest;
import com.teoware.refapp.dao.dto.InsertTaskResponse;

public interface TaskDao extends Serializable {

	public InsertTaskResponse insertTask(InsertTaskRequest request);
}
