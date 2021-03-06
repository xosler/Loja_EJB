--CREATE DATABASE learn_jaas;
--USE learn_jaas;
CREATE TABLE usertable(userid VARCHAR(10) PRIMARY KEY, password VARCHAR(32) NOT NULL);
CREATE TABLE grouptable(userid VARCHAR(10), groupid VARCHAR(20) NOT NULL, PRIMARY KEY (userid, groupid));
ALTER TABLE grouptable ADD CONSTRAINT FK_USERID FOREIGN KEY(userid) REFERENCES usertable(userid);
COMMIT;

INSERT INTO usertable VALUES ('user', 'user');
INSERT INTO grouptable VALUES ('user', 'users');
INSERT INTO usertable VALUES ('guest', 'guest');
INSERT INTO grouptable VALUES ('guest', 'users');
INSERT INTO usertable VALUES ('admin', 'admin');
INSERT INTO grouptable VALUES ('admin', 'admins');