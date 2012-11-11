package com.teoware.refapp.dao;
import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.InsertTaskRequest;
import com.teoware.refapp.dao.dto.InsertTaskResponse;

@Local
public interface TaskDao extends Serializable {

	public InsertTaskResponse insertTask(InsertTaskRequest request);
}
