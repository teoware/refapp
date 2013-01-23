package com.teoware.refapp.dao.metadata;

public interface TaskTables {

	// VIEWS
	public static final String TASKS_VIEW_NAME = "TASKS_V";

	// TASKS
	public static final String TASKS_TABLE_NAME = "TASKS";
	public static final String ID_COLUMN_NAME = "ID";
	public static final String USER_ID_COLUMN_NAME = "USER_ID";
	public static final String TITLE_COLUMN_NAME = "TITLE";

	// TASK_DETAILS
	public static final String TASK_DETAILS_TABLE_NAME = "TASK_DETAILS";
	public static final String TASK_ID_COLUMN_NAME = "TASK_ID";
	public static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";

	// TASK_STATUS
	public static final String TASK_STATUS_TABLE_NAME = "TASK_STATUS";
	public static final String STATUS_COLUMN_NAME = "STATUS";
	public static final String CREATED_COLUMN_NAME = "CREATED";
	public static final String MODIFIED_COLUMN_NAME = "MODIFIED";
}
