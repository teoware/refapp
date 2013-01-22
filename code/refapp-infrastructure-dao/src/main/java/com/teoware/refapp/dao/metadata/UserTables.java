package com.teoware.refapp.dao.metadata;

public interface UserTables {

	// VIEWS
	public static final String USERS_VIEW_NAME = "USERS_V";

	// USERS
	public static final String USERS_TABLE_NAME = "USERS";
	public static final String ID_COLUMN_NAME = "ID";
	public static final String USERNAME_COLUMN_NAME = "USERNAME";

	// USER_DETAILS
	public static final String USER_DETAILS_TABLE_NAME = "USER_DETAILS";
	public static final String USER_ID_COLUMN_NAME = "USER_ID";
	public static final String FIRSTNAME_COLUMN_NAME = "FIRSTNAME";
	public static final String LASTNAME_COLUMN_NAME = "LASTNAME";
	public static final String BIRTHDATE_COLUMN_NAME = "BIRTHDATE";
	public static final String GENDER_COLUMN_NAME = "GENDER";
	public static final String EMAIL_COLUMN_NAME = "EMAIL";
	public static final String PHONE_COLUMN_NAME = "PHONE";

	// USER_PASSWORD
	public static final String USER_PASSWORD_TABLE_NAME = "USER_PASSWORD";
	public static final String PASSWORD_COLUMN_NAME = "PASSWORD";
	public static final String SALT_COLUMN_NAME = "SALT";

	// USER_STATUS
	public static final String USER_STATUS_TABLE_NAME = "USER_STATUS";
	public static final String STATUS_COLUMN_NAME = "STATUS";
	public static final String CREATED_COLUMN_NAME = "CREATED";
	public static final String MODIFIED_COLUMN_NAME = "MODIFIED";

	// USER_ADDRESS
	public static final String USER_ADDRESS_TABLE_NAME = "USER_ADDRESS";
	public static final String ADDRESS_COLUMN_NAME = "ADDRESS";
	public static final String POSTALCODE_COLUMN_NAME = "POSTALCODE";
	public static final String MUNICIPALITY_COLUMN_NAME = "MUNICIPALITY";
	public static final String REGION_COLUMN_NAME = "REGION";
	public static final String COUNTRY_COLUMN_NAME = "COUNTRY";
}
