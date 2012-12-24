package com.teoware.refapp.dao.metadata;

public final class UsersTableMetaData {

	// VIEWS
	public static final String USERS_VIEW_NAME = "USERS_V";

	// USERS
	public static final String USERS_TABLE_NAME = "USERS";
	public static final String USERNAME_COLUMN_NAME = "USERNAME";
	public static final String FIRSTNAME_COLUMN_NAME = "FIRSTNAME";
	public static final String LASTNAME_COLUMN_NAME = "LASTNAME";
	public static final String BIRTHDATE_COLUMN_NAME = "BIRTHDATE";
	public static final String GENDER_COLUMN_NAME = "GENDER";
	public static final String EMAIL_COLUMN_NAME = "EMAIL";
	public static final String PHONE_COLUMN_NAME = "PHONE";

	// USERS_PASSWORD
	public static final String USERS_PASSWORD_TABLE_NAME = "USERS_PASSWORD";
	public static final String PASSWORD_COLUMN_NAME = "PASSWORD";
	public static final String SALT_COLUMN_NAME = "SALT";

	// USERS_STATUS
	public static final String USERS_STATUS_TABLE_NAME = "USERS_STATUS";
	public static final String STATUS_COLUMN_NAME = "STATUS";
	public static final String CREATED_COLUMN_NAME = "CREATED";
	public static final String MODIFIED_COLUMN_NAME = "MODIFIED";

	// USERS_ADDRESS
	public static final String USERS_ADDRESS_TABLE_NAME = "USERS_ADDRESS";
	public static final String ADDRESS_COLUMN_NAME = "ADDRESS";
	public static final String POSTALCODE_COLUMN_NAME = "POSTALCODE";
	public static final String MUNICIPALITY_COLUMN_NAME = "MUNICIPALITY";
	public static final String REGION_COLUMN_NAME = "REGION";
	public static final String COUNTRY_COLUMN_NAME = "COUNTRY";
}
