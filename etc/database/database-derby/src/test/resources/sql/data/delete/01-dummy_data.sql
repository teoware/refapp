DELETE FROM REFAPP.USER_STATUS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john');
DELETE FROM REFAPP.USER_STATUS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane');
DELETE FROM REFAPP.USER_STATUS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah');

DELETE FROM REFAPP.USER_ADDRESS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john');
DELETE FROM REFAPP.USER_ADDRESS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane');
DELETE FROM REFAPP.USER_ADDRESS WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah');

DELETE FROM REFAPP.USER_INFO WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'john');
DELETE FROM REFAPP.USER_INFO WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jane');
DELETE FROM REFAPP.USER_INFO WHERE USER_ID = (SELECT ID FROM REFAPP.USERS WHERE USERNAME = 'jonah');

DELETE FROM REFAPP.USERS WHERE USERNAME = 'john';
DELETE FROM REFAPP.USERS WHERE USERNAME = 'jane';
DELETE FROM REFAPP.USERS WHERE USERNAME = 'jonah';

DELETE FROM REFAPP.NOTE_STATUS WHERE NOTE_ID = (SELECT ID FROM REFAPP.NOTES WHERE TITLE = 'This is a new note for testing');

DELETE FROM REFAPP.NOTES WHERE TITLE = 'This is a new note for testing';