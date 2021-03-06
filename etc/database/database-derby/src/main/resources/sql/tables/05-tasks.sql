-- Table definitions

CREATE TABLE REFAPP.TASKS
(
	ID						BIGINT					GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID				BIGINT					NOT NULL,
	UUID					VARCHAR(36)			NOT NULL,
	CONSTRAINT		TASKS_PK				PRIMARY KEY (ID),
	CONSTRAINT		TASKS_FK1				FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE,
	CONSTRAINT		TASKS_UC1				UNIQUE (UUID)
);

CREATE TABLE REFAPP.TASK_STATUS
(
	ID						BIGINT							GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	TASK_ID				BIGINT							NOT NULL,
	STATUS				VARCHAR(10)					NOT NULL,
	CREATED				TIMESTAMP						NOT NULL,
	MODIFIED			TIMESTAMP						NOT NULL,
	CONSTRAINT		TASK_STATUS_PK			PRIMARY KEY (ID),
	CONSTRAINT		TASK_STATUS_FK1			FOREIGN KEY (TASK_ID) REFERENCES REFAPP.TASKS (ID) ON DELETE CASCADE,
	CONSTRAINT		TASK_STATUS_FK2			FOREIGN KEY (STATUS) REFERENCES REFAPP.REF_TASK_STATUS (STATUS),
	CONSTRAINT		TASK_STATUS_CC1			CHECK (MODIFIED >= CREATED)
);

CREATE TABLE REFAPP.TASK_DETAILS
(
	ID						BIGINT							GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	TASK_ID				BIGINT							NOT NULL,
	TITLE					VARCHAR(100)				NOT NULL,
	DESCRIPTION		VARCHAR(300)				,
	CONSTRAINT		TASK_DETAILS_PK			PRIMARY KEY (ID),
	CONSTRAINT		TASK_DETAILS_FK1		FOREIGN KEY (TASK_ID) REFERENCES REFAPP.TASKS (ID) ON DELETE CASCADE
);
