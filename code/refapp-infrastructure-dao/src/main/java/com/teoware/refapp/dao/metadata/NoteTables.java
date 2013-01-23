package com.teoware.refapp.dao.metadata;

public interface NoteTables {

	// VIEWS
	public static final String NOTES_VIEW_NAME = "NOTES_V";

	// NOTES
	public static final String NOTES_TABLE_NAME = "NOTES";
	public static final String ID_COLUMN_NAME = "ID";
	public static final String USER_ID_COLUMN_NAME = "USER_ID";
	public static final String TITLE_COLUMN_NAME = "TITLE";

	// NOTE_DETAILS
	public static final String NOTE_DETAILS_TABLE_NAME = "NOTE_DETAILS";
	public static final String NOTE_ID_COLUMN_NAME = "NOTE_ID";
	public static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";

	// NOTE_STATUS
	public static final String NOTE_STATUS_TABLE_NAME = "NOTE_STATUS";
	public static final String STATUS_COLUMN_NAME = "STATUS";
	public static final String CREATED_COLUMN_NAME = "CREATED";
	public static final String MODIFIED_COLUMN_NAME = "MODIFIED";
}
