-- Trigger definitions

CREATE TRIGGER REFAPP.AUTHORS_TRG01
	AFTER INSERT ON REFAPP.AUTHORS
	REFERENCING NEW AS NEWROW
	FOR EACH ROW MODE DB2SQL
		INSERT INTO REFAPP.AUTHORS_STATUS (AUTHOR, STATUS, CREATED, MODIFIED)
		VALUES (NEWROW.USERNAME, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

CREATE TRIGGER REFAPP.AUTHORS_TRG02
	AFTER UPDATE ON REFAPP.AUTHORS
	REFERENCING OLD AS OLDROW
	FOR EACH ROW MODE DB2SQL
		UPDATE REFAPP.AUTHORS_STATUS SET MODIFIED = CURRENT_TIMESTAMP
		WHERE AUTHOR = OLDROW.USERNAME
;
