-- Quét đoạn này chạy trước
CREATE DATABASE finalproject_chatapplication

-- Postgres không cho sử dụng use finalproject_chatapplication
-- Nên sau đó kết nối vào database finalpeoject_chatapplication và chạy hết đoạn bên dưới
CREATE TABLE USER_ACCOUNT (
	ID SERIAL,
    	USERNAME varchar(20),
    	PASSWORD varchar(20),
	FULLNAME varchar(50),
	ADDRESS varchar(50),
	DATE_OF_BIRTH date,
	GENDER varchar(3),
	EMAIL VARCHAR,
	ONLINE BOOLEAN,
	CREATED_AT TIMESTAMP,
	primary key(ID)
);

create table USER_FRIEND (
	ID INTEGER REFERENCES USER_ACCOUNT(ID),
	FRIEND_ID INTEGER REFERENCES USER_ACCOUNT(ID),
	primary key(ID, FRIEND_ID)
);

CREATE TABLE FRIEND_REQUEST(
	FROM_ID INTEGER REFERENCES USER_ACCOUNT(ID),
	TO_ID INTEGER REFERENCES USER_ACCOUNT(ID),
	STATUS VARCHAR,
	TRY INTEGER,
	primary key(FROM_ID, TO_ID)
);

CREATE TABLE LOGIN_HISTORY(
	LOGIN_ID SERIAL PRIMARY KEY,
	USER_ID INTEGER,
	LOGIN_TIME TIMESTAMP,
	CONSTRAINT FK_LOGIN_HISTORY_USER_ACCOUNT FOREIGN KEY (USER_ID) REFERENCES USER_ACCOUNT(ID)
);



CREATE TABLE MESSAGE_USER(
	ID BIGSERIAL PRIMARY KEY,
	CHATBOX_ID VARCHAR(10),
	FROM_USER INTEGER REFERENCES USER_ACCOUNT(ID),
	TO_USER INTEGER REFERENCES USER_ACCOUNT(ID),
	TIME_SEND TIMESTAMP,
	CONTENT TEXT,
	VISIBLE_ONLY INTEGER REFERENCES USER_ACCOUNT(ID) 
);


create table GROUPCHAT (
	ID SERIAL PRIMARY KEY,
	GROUP_NAME VARCHAR(50),
	CREATED_AT TIMESTAMP,
	ONLINE BOOLEAN
);

create table GROUPCHAT_MEMBER (
	GROUPCHAT_ID INTEGER references GROUPCHAT(ID),
	MEMBER_ID INTEGER references USER_ACCOUNT(ID),
	POSITION VARCHAR(20),
	primary key(GROUPCHAT_ID,MEMBER_ID)
);

CREATE TABLE MESSAGE_GROUP(
	ID BIGSERIAL PRIMARY KEY,
	FROM_USER INTEGER REFERENCES USER_ACCOUNT(ID),
	TO_GROUP INTEGER REFERENCES GROUPCHAT(ID),
	CHAT_TIME TIMESTAMP,
	CONTENT TEXT
);
