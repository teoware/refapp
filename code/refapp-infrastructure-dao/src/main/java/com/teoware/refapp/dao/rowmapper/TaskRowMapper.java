package com.teoware.refapp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.metadata.TaskTables;
import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.task.Task;
import com.teoware.refapp.model.util.BeanFactory;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		Task task = BeanFactory.createTask();

		task.getUuid().setUuid(result.getString(TaskTables.UUID_COLUMN_NAME));
		task.getTaskDetails().setTitle(result.getString(TaskTables.TITLE_COLUMN_NAME));
		task.getTaskDetails().setDescription(result.getString(TaskTables.DESCRIPTION_COLUMN_NAME));
		task.getTaskStatus().setStatus(MapperHelper.mapStatus(result.getString(TaskTables.STATUS_COLUMN_NAME)));
		task.getTaskStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(TaskTables.CREATED_COLUMN_NAME)));
		task.getTaskStatus().setModified(
				MapperHelper.mapTimestamp(result.getTimestamp(TaskTables.MODIFIED_COLUMN_NAME)));

		return task;
	}
}
