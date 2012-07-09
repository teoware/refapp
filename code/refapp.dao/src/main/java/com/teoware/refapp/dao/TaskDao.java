package com.teoware.refapp.dao;
import java.io.Serializable;

import com.teoware.refapp.dao.message.InsertTaskRequest;
import com.teoware.refapp.dao.message.InsertTaskResponse;

public interface TaskDao extends Serializable {

	public InsertTaskResponse insertTask(InsertTaskRequest insertTaskRequest);
}
