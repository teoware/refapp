package com.teoware.refapp.dao.metadata;

public interface NoteTables {

	// VIEWS
	static final String NOTES_VIEW_NAME = "NOTES_V";

	// NOTES
	static final String NOTES_TABLE_NAME = "NOTES";
	static final String ID_COLUMN_NAME = "ID";
	static final String USER_ID_COLUMN_NAME = "USER_ID";
	static final String TITLE_COLUMN_NAME = "TITLE";

	// NOTE_DETAILS
	static final String NOTE_DETAILS_TABLE_NAME = "NOTE_DETAILS";
	static final String NOTE_ID_COLUMN_NAME = "NOTE_ID";
	static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";

	// NOTE_STATUS
	static final String NOTE_STATUS_TABLE_NAME = "NOTE_STATUS";
	static final String STATUS_COLUMN_NAME = "STATUS";
	static final String CREATED_COLUMN_NAME = "CREATED";
	static final String MODIFIED_COLUMN_NAME = "MODIFIED";
}
