package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.TaskTables.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.TaskTables.DESCRIPTION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.TaskTables.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.TaskTables.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.TaskTables.TITLE_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.task.Task;
import com.teoware.refapp.model.util.BeanFactory;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		Task task = BeanFactory.createTask();

		task.getTitle().setTitle(result.getString(TITLE_COLUMN_NAME));
		task.getTaskDetails().setDescription(result.getString(DESCRIPTION_COLUMN_NAME));
		task.getTaskStatus().setStatus(MapperHelper.mapStatus(result.getString(STATUS_COLUMN_NAME)));
		task.getTaskStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(CREATED_COLUMN_NAME)));
		task.getTaskStatus().setModified(MapperHelper.mapTimestamp(result.getTimestamp(MODIFIED_COLUMN_NAME)));

		return task;
	}
}
