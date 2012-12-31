-- Ref table definitions

CREATE TABLE REFAPP.REF_USER_STATUS
(
	STATUS		VARCHAR(10) NOT NULL,
	DESCRIPTION	VARCHAR(50) NOT NULL,
	CONSTRAINT REF_USER_STATUS_UC1 UNIQUE (STATUS)
);

CREATE TABLE REFAPP.REF_NOTE_STATUS
(
	STATUS		VARCHAR(10) NOT NULL,
	DESCRIPTION	VARCHAR(50) NOT NULL,
	CONSTRAINT REF_NOTE_STATUS_UC1 UNIQUE (STATUS)
);

CREATE TABLE REFAPP.REF_TASK_STATUS
(
	STATUS		VARCHAR(10) NOT NULL,
	DESCRIPTION	VARCHAR(50) NOT NULL,
	CONSTRAINT REF_TASK_STATUS_UC1 UNIQUE (STATUS)
);

CREATE TABLE REFAPP.REF_GENDER
(
	GENDER		VARCHAR(10) NOT NULL,
	DESCRIPTION	VARCHAR(50) NOT NULL,
	CONSTRAINT REF_GENDER_UC1 UNIQUE (GENDER)
);
