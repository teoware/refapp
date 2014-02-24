package com.teoware.refapp.dao.metadata;

public interface UserTables {

    // VIEWS
    static final String USERS_VIEW_NAME = "USERS_V";

    // USERS
    static final String USERS_TABLE_NAME = "USERS";
    static final String ID_COLUMN_NAME = "ID";
    static final String USERNAME_COLUMN_NAME = "USERNAME";

    // USER_DETAILS
    static final String USER_DETAILS_TABLE_NAME = "USER_DETAILS";
    static final String USER_ID_COLUMN_NAME = "USER_ID";
    static final String FIRSTNAME_COLUMN_NAME = "FIRSTNAME";
    static final String LASTNAME_COLUMN_NAME = "LASTNAME";
    static final String BIRTHDATE_COLUMN_NAME = "BIRTHDATE";
    static final String GENDER_COLUMN_NAME = "GENDER";
    static final String EMAIL_COLUMN_NAME = "EMAIL";
    static final String PHONE_COLUMN_NAME = "PHONE";

    // USER_PASSWORD
    static final String USER_PASSWORD_TABLE_NAME = "USER_PASSWORD";
    static final String PASSWORD_COLUMN_NAME = "PASSWORD";
    static final String SALT_COLUMN_NAME = "SALT";

    // USER_STATUS
    static final String USER_STATUS_TABLE_NAME = "USER_STATUS";
    static final String STATUS_COLUMN_NAME = "STATUS";
    static final String CREATED_COLUMN_NAME = "CREATED";
    static final String MODIFIED_COLUMN_NAME = "MODIFIED";

    // USER_ADDRESS
    static final String USER_ADDRESS_TABLE_NAME = "USER_ADDRESS";
    static final String ADDRESS_COLUMN_NAME = "ADDRESS";
    static final String POSTALCODE_COLUMN_NAME = "POSTALCODE";
    static final String MUNICIPALITY_COLUMN_NAME = "MUNICIPALITY";
    static final String REGION_COLUMN_NAME = "REGION";
    static final String COUNTRY_COLUMN_NAME = "COUNTRY";
}
