INSERT INTO REFAPP.USERS (USERNAME) VALUES ('john');
INSERT INTO REFAPP.USERS (USERNAME) VALUES ('jane');
INSERT INTO REFAPP.USERS (USERNAME) VALUES ('jonah');

INSERT INTO REFAPP.USER_INFO (USER_ID, FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, EMAIL, PHONE) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john'), 'John', 'Doe', DATE('1970-01-01'), 'MALE', 'john.doe@gmail.com', '+47 12345678');
INSERT INTO REFAPP.USER_INFO (USER_ID, FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, EMAIL, PHONE) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane'), 'Jane', 'Doe', DATE('1970-01-02'), 'FEMALE', 'jane.doe@gmail.com', '+47 12345678');
INSERT INTO REFAPP.USER_INFO (USER_ID, FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, EMAIL, PHONE) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah'), 'Jonah', 'Doe', DATE('1990-01-03'), 'MALE', 'jonah.doe@gmail.com', '+47 12345678');

UPDATE REFAPP.USER_STATUS SET STATUS = 'ACTIVE' WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john');
UPDATE REFAPP.USER_STATUS SET STATUS = 'ACTIVE' WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane');
UPDATE REFAPP.USER_STATUS SET STATUS = 'ACTIVE' WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah');

INSERT INTO REFAPP.USER_ADDRESS (USER_ID, ADDRESS, POSTALCODE, MUNICIPALITY, REGION, COUNTRY) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john'), NULL, NULL, NULL, NULL, NULL);
INSERT INTO REFAPP.USER_ADDRESS (USER_ID, ADDRESS, POSTALCODE, MUNICIPALITY, REGION, COUNTRY) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane'), NULL, NULL, NULL, NULL, NULL);
INSERT INTO REFAPP.USER_ADDRESS (USER_ID, ADDRESS, POSTALCODE, MUNICIPALITY, REGION, COUNTRY) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah'), NULL, NULL, NULL, NULL, NULL);

INSERT INTO REFAPP.NOTES (USER_ID, TITLE) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john'), 'This is a new note for testing');

INSERT INTO REFAPP.NOTE_DETAILS (NOTE_ID, DESCRIPTION) VALUES ((SELECT ID FROM REFAPP.NOTES WHERE TITLE = 'This is a new note for testing'), 'Blah blah...');

INSERT INTO REFAPP.NOTE_STATUS (NOTE_ID, STATUS, CREATED, MODIFIED) VALUES ((SELECT ID FROM REFAPP.NOTES WHERE TITLE = 'This is a new note for testing'), 'NEW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO REFAPP.TASKS (USER_ID, TITLE) VALUES ((SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john'), 'This is a new task for testing');

INSERT INTO REFAPP.TASK_DETAILS (TASK_ID, DESCRIPTION) VALUES ((SELECT ID FROM REFAPP.TASKS WHERE TITLE = 'This is a new task for testing'), 'Blah blah...');

INSERT INTO REFAPP.TASK_STATUS (TASK_ID, STATUS, CREATED, MODIFIED) VALUES ((SELECT ID FROM REFAPP.TASKS WHERE TITLE = 'This is a new task for testing'), 'NEW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
