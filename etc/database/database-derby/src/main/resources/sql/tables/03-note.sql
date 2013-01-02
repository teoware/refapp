-- Table definitions

CREATE TABLE REFAPP.NOTES
(
	ID			BIGINT			GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	USER_ID		BIGINT			NOT NULL,
	TITLE		VARCHAR(100)	NOT NULL,
	NOTE		VARCHAR(300),
	CONSTRAINT	NOTES_PK		PRIMARY KEY (ID),
	CONSTRAINT	NOTES_FK1		FOREIGN KEY (USER_ID) REFERENCES REFAPP.USERS (ID) ON DELETE CASCADE
);

CREATE TABLE REFAPP.NOTE_STATUS
(
	ID			BIGINT				GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	NOTE_ID		BIGINT				NOT NULL,
	STATUS		VARCHAR(10)			NOT NULL,
	CREATED		TIMESTAMP			NOT NULL,
	MODIFIED	TIMESTAMP			NOT NULL,
	CONSTRAINT	NOTE_STATUS_PK		PRIMARY KEY (ID),
	CONSTRAINT	NOTE_STATUS_FK1		FOREIGN KEY (NOTE_ID) REFERENCES REFAPP.NOTES (ID),
	CONSTRAINT	NOTE_STATUS_FK2		FOREIGN KEY (STATUS) REFERENCES REFAPP.REF_NOTE_STATUS (STATUS),
	CONSTRAINT	NOTE_STATUS_CC1		CHECK (MODIFIED >= CREATED)
);
