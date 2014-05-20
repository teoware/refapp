-- Table definitions

CREATE TABLE REFAPP.USERS
(
	ID						BIGINT					GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USERNAME			VARCHAR(20)			NOT NULL,
	CONSTRAINT		USERS_PK				PRIMARY KEY (ID),
	CONSTRAINT		USERS_UC1				UNIQUE (USERNAME)
);

CREATE TABLE REFAPP.USER_STATUS
(
	ID						BIGINT							GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID				BIGINT							NOT NULL,
	STATUS				VARCHAR(10)					NOT NULL,
	CREATED				TIMESTAMP						NOT NULL,
	MODIFIED			TIMESTAMP						NOT NULL,
	CONSTRAINT		USER_STATUS_PK			PRIMARY KEY (ID),
	CONSTRAINT		USER_STATUS_FK1			FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE,
	CONSTRAINT		USER_STATUS_FK2			FOREIGN KEY (STATUS) REFERENCES REFAPP.REF_USER_STATUS (STATUS),
	CONSTRAINT		USER_STATUS_CC1			CHECK (MODIFIED >= CREATED)
);

CREATE TABLE REFAPP.USER_DETAILS
(
	ID						BIGINT						GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID				BIGINT						NOT NULL,
	FIRSTNAME			VARCHAR(50)				NOT NULL,
	LASTNAME			VARCHAR(50)				NOT NULL,
	BIRTHDATE			DATE							NOT NULL,
	GENDER				VARCHAR(10)				NOT NULL,
	EMAIL					VARCHAR(50)				NOT NULL,
	PHONE					VARCHAR(50)				,
	CONSTRAINT		USER_INFO_PK			PRIMARY KEY (ID),
	CONSTRAINT		USER_INFO_FK1			FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE,
	CONSTRAINT		USER_INFO_FK2			FOREIGN KEY (GENDER) REFERENCES REFAPP.REF_GENDER (GENDER),
	CONSTRAINT		USER_INFO_UC1			UNIQUE (EMAIL)
);

CREATE TABLE REFAPP.USER_PASSWORD
(
	ID						BIGINT							GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID				BIGINT							NOT NULL,
	PASSWORD			VARCHAR(100)				NOT NULL,
	SALT					VARCHAR(100)				NOT NULL,
	CONSTRAINT		USER_PASSWORD_PK		PRIMARY KEY (ID),
	CONSTRAINT		USER_PASSWORD_FK1		FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE
);

CREATE TABLE REFAPP.USER_ADDRESS
(
	ID						BIGINT							GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID				BIGINT							NOT NULL,
	ADDRESS				VARCHAR(50)					,
	POSTALCODE		VARCHAR(10)					,
	MUNICIPALITY	VARCHAR(50)					,
	REGION				VARCHAR(50)					,
	COUNTRY				VARCHAR(50)					,
	CONSTRAINT		USER_ADDRESS_PK			PRIMARY KEY (ID),
	CONSTRAINT		USER_ADDRESS_FK1		FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE
);
