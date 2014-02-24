package com.teoware.refapp.service;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.service.dto.ChangeTaskRequest;
import com.teoware.refapp.service.dto.ChangeTaskResponse;
import com.teoware.refapp.service.dto.CreateTaskRequest;
import com.teoware.refapp.service.dto.CreateTaskResponse;
import com.teoware.refapp.service.dto.DeleteTaskRequest;
import com.teoware.refapp.service.dto.DeleteTaskResponse;
import com.teoware.refapp.service.dto.FindTaskRequest;
import com.teoware.refapp.service.dto.FindTaskResponse;
import com.teoware.refapp.service.dto.ListTasksRequest;
import com.teoware.refapp.service.dto.ListTasksResponse;

@Local
public interface TaskService extends Serializable {

    public CreateTaskResponse createTask(CreateTaskRequest request);

    public FindTaskResponse findTask(FindTaskRequest request) throws ServiceException;

    public ListTasksResponse listTasks(ListTasksRequest request) throws ServiceException;

    public ChangeTaskResponse changeTask(ChangeTaskRequest request) throws ServiceException;

    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) throws ServiceException;
}
