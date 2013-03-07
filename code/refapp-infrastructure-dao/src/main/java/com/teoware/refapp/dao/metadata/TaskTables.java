package com.teoware.refapp.dao.metadata;

public interface TaskTables {

	// VIEWS
	static final String TASKS_VIEW_NAME = "TASKS_V";

	// TASKS
	static final String TASKS_TABLE_NAME = "TASKS";
	static final String ID_COLUMN_NAME = "ID";
	static final String USER_ID_COLUMN_NAME = "USER_ID";
	static final String TITLE_COLUMN_NAME = "TITLE";

	// TASK_DETAILS
	static final String TASK_DETAILS_TABLE_NAME = "TASK_DETAILS";
	static final String TASK_ID_COLUMN_NAME = "TASK_ID";
	static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";

	// TASK_STATUS
	static final String TASK_STATUS_TABLE_NAME = "TASK_STATUS";
	static final String STATUS_COLUMN_NAME = "STATUS";
	static final String CREATED_COLUMN_NAME = "CREATED";
	static final String MODIFIED_COLUMN_NAME = "MODIFIED";
}
