-- Quét đoạn này chạy trước
-- CREATE DATABASE finalproject_chatapplication

-- Postgres không cho sử dụng use finalproject_chatapplication
-- Nên sau đó kết nối vào database finalpeoject_chatapplication và chạy hết đoạn bên dưới
CREATE TABLE USER_ACCOUNT (
	ID SERIAL,
    	USERNAME varchar,
    	PASSWORD varchar,
	FULLNAME varchar,
	ADDRESS varchar,
	DATE_OF_BIRTH date,
	GENDER varchar(3),
	EMAIL VARCHAR,
	ONLINE BOOLEAN,
	CREATED_AT TIMESTAMP,
	BANNED BOOLEAN,
	primary key(ID)
);

create table USER_FRIEND (
	ID INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	FRIEND_ID INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	primary key(ID, FRIEND_ID)
);

CREATE TABLE FRIEND_REQUEST(
	FROM_ID INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	TO_ID INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	STATUS VARCHAR,
	TRY INTEGER,
	primary key(FROM_ID, TO_ID)
);

CREATE TABLE LOGIN_HISTORY(
	LOGIN_ID SERIAL PRIMARY KEY,
	USER_ID INTEGER,
	LOGIN_TIME TIMESTAMP,
	CONSTRAINT FK_LOGIN_HISTORY_USER_ACCOUNT FOREIGN KEY (USER_ID) REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE
);





CREATE TABLE MESSAGE_USER(
	ID BIGSERIAL PRIMARY KEY,
	CHATBOX_ID VARCHAR,
	FROM_USER INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	TO_USER INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	TIME_SEND TIMESTAMP,
	CONTENT TEXT,
	VISIBLE_ONLY INTEGER 
);


create table GROUPCHAT (
	ID SERIAL PRIMARY KEY,
	GROUP_NAME VARCHAR,
	CREATED_AT TIMESTAMP,
	ONLINE BOOLEAN
);

create table GROUPCHAT_MEMBER (
	GROUPCHAT_ID INTEGER references GROUPCHAT(ID),
	MEMBER_ID INTEGER references USER_ACCOUNT(ID) ON DELETE CASCADE,
	POSITION VARCHAR(20),
	primary key(GROUPCHAT_ID,MEMBER_ID)
);

CREATE TABLE MESSAGE_GROUP(
	ID BIGSERIAL PRIMARY KEY,
	FROM_USER INTEGER REFERENCES USER_ACCOUNT(ID) ON DELETE CASCADE,
	TO_GROUP INTEGER REFERENCES GROUPCHAT(ID),
	TIME_SEND TIMESTAMP,
	CONTENT TEXT
);

-- ADD DATA

ALTER SEQUENCE USER_ACCOUNT_id_seq RESTART WITH 1;


--USER ACCOUNT
insert into USER_ACCOUNT (USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, ONLINE,BANNED)
values
				('USER1','123456',N'Nguyễn Mạnh Hùng', N'Địa chỉ 1', '1990-01-14', N'Nam', '1','0'),
				('USER2','123456',N'Nguyễn Mạnh Thủy', N'Địa chỉ 2', '1994-12-08', N'Nữ', '1','0'),
				('USER3','123456',N'Nguyễn Mạnh Hà', N'Địa chỉ 3', '1998-09-03', N'Nữ', '1','0'),
				('USER4','123456',N'Nguyễn Mạnh Thu', N'Địa chỉ 4', '1998-09-03', N'Nữ', '0','0'),
				('USER5','123456',N'Nguyễn Mạnh Mai', N'Địa chỉ 5', '2003-03-26', N'Nữ', '1','0'),
				('USER6','123456',N'Nguyễn Mạnh Vy', N'Địa chỉ 6', '2000-02-14', N'Nữ', '1','0'),
				('USER7','123456',N'Nguyễn Mạnh Nam', N'Địa chỉ 7', '1991-05-06', 'Nam', '0','0'),
				('USER8','123456',N'Nguyễn Mạnh An', N'Địa chỉ 8', '1996-08-19', 'Nam', '1','0'),
				('USER9','123456',N'Nguyễn Mạnh Nguyệt', N'Địa chỉ 9', '2006-01-14', N'Nữ', '0','0');
				
				
INSERT INTO LOGIN_HISTORY(USER_ID,LOGIN_TIME)
VALUES
	(1, '2022-12-15 20:30:00'),
	(3, '2022-12-17 23:55:40'),
	( 3, '2022-12-17 08:42:05'),
	(1, '2022-12-18 10:17:21'),
	( 2, '2022-12-19 15:25:00'),
	( 6, '2022-12-20 22:52:40');

-- USER FRIEND
insert into USER_FRIEND (ID, FRIEND_ID)
values
				(1, 3),
				(1, 4),
				(1, 9),
				(2, 3),
				(3, 1),
				(3, 2),
				(4, 1),
				(4, 6),
				(6, 4),
				(9, 1);

-- 
insert into FRIEND_REQUEST (FROM_ID, TO_ID,STATUS,TRY)
values
				(5, 1,'WAIT',0),
				(6, 1,'WAIT',0),
				(4, 1,'ACCEPTED',0),
				(2, 4,'WAIT',0),
				(2, 6,'DENIED',0);
				
				

-- CHAT_HISTORY
INSERT INTO MESSAGE_USER (CHATBOX_ID,FROM_USER, TO_USER, TIME_SEND, CONTENT,VISIBLE_ONLY)
values
				('1-3',1, 3, '2022-12-18 22:52:40', 'Hôm nay ông thế nào rồi',-90),
				('2-3',2, 3, '2022-12-18 22:55:40', 'Mai nhớ đem tập',-90),
				('3-4',4, 3, '2022-12-18 23:52:40', 'Tập bữa mượn đâu rồi',-90),
				('1-3',3, 1, '2022-12-18 22:52:40', 'Bữa nay thấy không đi học',-90),
				('2-3',3, 2, '2022-12-18 22:52:40', 'Nó đòi t rồi',-90),
				('4-6',4, 6, '2022-12-18 24:52:40', 'Ok',-90),
				('4-6',6, 4, '2022-12-18 22:52:40', 'Bye',-90),
				('1-9',9, 1, '2022-12-18 22:52:40', 'Cuối tuần rảnh không!',-90);


-- GROUPCHAT
insert into GROUPCHAT (GROUP_NAME,CREATED_AT,ONLINE)
values
				('Group1', '2022-12-18 22:52:40','1'),
				('Group2', '2022-12-18 22:52:40','0'),
				('Group3', '2022-12-18 22:52:40','1');
-- GROUPCHAT MEMBER
insert into GROUPCHAT_MEMBER (GROUPCHAT_ID, MEMBER_ID,POSITION)
values
				(1, 1,'admin'),
				(1, 2,'member'),
				(1, 3,'member'),
				(2, 2,'admin'),
				(3, 3,'admin');		


INSERT INTO MESSAGE_GROUP (FROM_USER, TO_GROUP, TIME_SEND, CONTENT)
values
				(1, 1, '2022-12-18 22:52:40', 'Còn công việc cuối là cái này nhưng t để nhầm file làm việc, phải là ở uichatcomponent/DetailAccounForm.java'),
				(1, 1, '2022-12-18 22:52:40', 'Deadline là 9h tối nay, k có thì t làm lun'),
				(2, 1, '2022-12-18 22:52:40', 'Oki t có lm cái sườn r'),
				(3, 1, '2022-12-18 22:52:40', 'cần full tất cả các cột ko ông hay mấy cột chính thôi'),
				(3, 3, '2022-12-18 22:52:40', 'Message6'),
				(1, 1, '2022-12-18 22:52:40', 'tranh thủ tối nay xong cái này nhé'),
				(2, 2, '2022-12-18 22:52:40', 'Message8');

