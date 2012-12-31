package com.teoware.refapp.dao.test;

public final class TestDataSourceMetaData {

	public static final String CREATE_SCHEMA_REFAPP_STATEMENT = "CREATE SCHEMA REFAPP AUTHORIZATION DBA";

	public static final String CREATE_TABLE_REF_USER_STATUS_STATEMENT = "CREATE TABLE REFAPP.REF_USER_STATUS ("
			+ "STATUS VARCHAR(10) NOT NULL, " + "DESCRIPTION VARCHAR(50) NOT NULL, "
			+ "CONSTRAINT REF_USERS_STATUS_UC UNIQUE (STATUS))";

	public static final String CREATE_TABLE_REF_GENDER_STATEMENT = "CREATE TABLE REFAPP.REF_GENDER ("
			+ "GENDER VARCHAR(10) NOT NULL, " + "DESCRIPTION VARCHAR(50) NOT NULL, "
			+ "CONSTRAINT REF_GENDER_UC UNIQUE (GENDER))";

	public static final String CREATE_TABLE_USERS_STATEMENT = "CREATE TABLE REFAPP.USERS ("
			+ "USERNAME VARCHAR(20) NOT NULL, " + "FIRSTNAME VARCHAR(50) NOT NULL, "
			+ "LASTNAME VARCHAR(50) NOT NULL, " + "BIRTHDATE DATE NOT NULL, " + "GENDER VARCHAR(10) NOT NULL, "
			+ "EMAIL VARCHAR(50) NOT NULL, " + "PHONE VARCHAR(50), " + "CONSTRAINT USERS_PK PRIMARY KEY (USERNAME), "
			+ "CONSTRAINT USERS_FK1 FOREIGN KEY (GENDER) REFERENCES REFAPP.REF_GENDER (GENDER), "
			+ "CONSTRAINT USERS_UC1 UNIQUE (EMAIL))";

	public static final String CREATE_TABLE_USER_STATUS_STATEMENT = "CREATE TABLE REFAPP.USER_STATUS ("
			+ "USERNAME VARCHAR(20) NOT NULL, "
			+ "STATUS VARCHAR(10) NOT NULL, "
			+ "CREATED DATE NOT NULL, "
			+ "MODIFIED	DATE NOT NULL, "
			+ "CONSTRAINT USERS_STATUS_PK	PRIMARY KEY (USERNAME), "
			+ "CONSTRAINT USERS_STATUS_FK1 FOREIGN KEY (USERNAME) REFERENCES REFAPP.USERS (USERNAME) ON DELETE CASCADE, "
			+ "CONSTRAINT USERS_STATUS_FK2 FOREIGN KEY (STATUS) REFERENCES REFAPP.REF_USER_STATUS (STATUS), "
			+ "CONSTRAINT USERS_STATUS_CC1 CHECK (MODIFIED >= CREATED))";

	public static final String CREATE_TABLE_USER_PASSWORD_STATEMENT = "CREATE TABLE REFAPP.USER_PASSWORD ("
			+ "USERNAME VARCHAR(20) NOT NULL, "
			+ "PASSWORD	VARCHAR(100) NOT NULL, "
			+ "SALT	VARCHAR(100) NOT NULL, "
			+ "CONSTRAINT USERS_PASSWORD_PK PRIMARY KEY (USERNAME), "
			+ "CONSTRAINT USERS_PASSWORD_FK1 FOREIGN KEY (USERNAME) REFERENCES REFAPP.USERS (USERNAME) ON DELETE CASCADE)";

	public static final String CREATE_TABLE_USER_ADDRESS_STATEMENT = "CREATE TABLE REFAPP.USER_ADDRESS ("
			+ "USERNAME VARCHAR(20) NOT NULL, "
			+ "ADDRESS VARCHAR(50), "
			+ "POSTALCODE VARCHAR(10), "
			+ "MUNICIPALITY VARCHAR(50), "
			+ "REGION VARCHAR(50), "
			+ "COUNTRY VARCHAR(50), "
			+ "CONSTRAINT USERS_ADDRESS_PK PRIMARY KEY (USERNAME), "
			+ "CONSTRAINT USERS_ADDRESS_FK1 FOREIGN KEY (USERNAME) REFERENCES REFAPP.USERS (USERNAME) ON DELETE CASCADE)";

	public static final String CREATE_VIEW_USERS_V_STATEMENT = "CREATE VIEW REFAPP.USERS_V ("
			+ "USERNAME, FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, EMAIL, PHONE, "
			+ "ADDRESS, POSTALCODE, MUNICIPALITY, REGION, COUNTRY, STATUS, CREATED, MODIFIED) "
			+ "AS SELECT U.USERNAME, U.FIRSTNAME, U.LASTNAME, U.BIRTHDATE, U.GENDER, U.EMAIL, U.PHONE, "
			+ "D.ADDRESS, D.POSTALCODE, D.MUNICIPALITY, D.REGION, D.COUNTRY, S.STATUS, S.CREATED, S.MODIFIED "
			+ "FROM REFAPP.USERS U, REFAPP.USER_ADDRESS D, REFAPP.USER_STATUS S "
			+ "WHERE U.USERNAME = S.USERNAME AND U.USERNAME = D.USERNAME";

	public static final String CREATE_TRIGGER_USERS_TRG1_STATEMENT = "CREATE TRIGGER REFAPP.USERS_TRG1 "
			+ "AFTER INSERT ON REFAPP.USERS REFERENCING NEW ROW AS NEWROW FOR EACH ROW BEGIN ATOMIC "
			+ "INSERT INTO REFAPP.USER_STATUS (USERNAME, STATUS, CREATED, MODIFIED) "
			+ "VALUES (NEWROW.USERNAME, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); END";
	
	public static final String CREATE_TRIGGER_USERS_TRG2_STATEMENT = "CREATE TRIGGER REFAPP.USERS_TRG2 "
			+ "AFTER UPDATE ON REFAPP.USERS REFERENCING OLD ROW AS OLDROW FOR EACH ROW BEGIN ATOMIC "
			+ "UPDATE REFAPP.USER_STATUS SET MODIFIED = CURRENT_TIMESTAMP WHERE USERNAME = OLDROW.USERNAME; END";

	public static final String INSERT_REF_USER_STATUS_STATEMENT_1 = "INSERT INTO REFAPP.REF_USER_STATUS "
			+ "VALUES ('PENDING', 'User is pending activation')";

	public static final String INSERT_REF_USER_STATUS_STATEMENT_2 = "INSERT INTO REFAPP.REF_USER_STATUS "
			+ "VALUES ('ACTIVE', 'User is active')";

	public static final String INSERT_REF_USER_STATUS_STATEMENT_3 = "INSERT INTO REFAPP.REF_USER_STATUS "
			+ "VALUES ('SUSPENDED', 'User is suspended')";

	public static final String INSERT_REF_USER_STATUS_STATEMENT_4 = "INSERT INTO REFAPP.REF_USER_STATUS "
			+ "VALUES ('DELETED', 'User has been deleted')";

	public static final String INSERT_REF_GENDER_STATEMENT_1 = "INSERT INTO REFAPP.REF_GENDER "
			+ "VALUES ('MALE', 'Male')";

	public static final String INSERT_REF_GENDER_STATEMENT_2 = "INSERT INTO REFAPP.REF_GENDER "
			+ "VALUES ('FEMALE', 'Female')";
}
