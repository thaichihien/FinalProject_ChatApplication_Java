
-- use MyChatRoom
-- go


-- -- select data from table
-- SELECT * FROM USER_ACCOUNT
-- SELECT * FROM USER_FRIEND
-- SELECT * FROM LOGIN_HISTORY
-- SELECT * FROM GROUPCHAT
-- SELECT * FROM GROUPCHAT_MEMBER
-- SELECT * FROM MESSAGE_USER

ALTER SEQUENCE USER_ACCOUNT_id_seq RESTART WITH 1;


--USER ACCOUNT
insert into USER_ACCOUNT (USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, ONLINE)
values
				('USER1','123456',N'Nguyễn Mạnh Hùng', N'Địa chỉ 1', '1990-01-14', N'Nam', '1'),
				('USER2','123456',N'Nguyễn Mạnh Thủy', N'Địa chỉ 2', '1994-12-08', N'Nữ', '1'),
				('USER3','123456',N'Nguyễn Mạnh Hà', N'Địa chỉ 3', '1998-09-03', N'Nữ', '1'),
				('USER4','123456',N'Nguyễn Mạnh Thu', N'Địa chỉ 4', '1998-09-03', N'Nữ', '0'),
				('USER5','123456',N'Nguyễn Mạnh Mai', N'Địa chỉ 5', '2003-03-26', N'Nữ', '1'),
				('USER6','123456',N'Nguyễn Mạnh Vy', N'Địa chỉ 6', '2000-02-14', N'Nữ', '1'),
				('USER7','123456',N'Nguyễn Mạnh Nam', N'Địa chỉ 7', '1991-05-06', 'Nam', '0'),
				('USER8','123456',N'Nguyễn Mạnh An', N'Địa chỉ 8', '1996-08-19', 'Nam', '1'),
				('USER9','123456',N'Nguyễn Mạnh Nguyệt', N'Địa chỉ 9', '2006-01-14', N'Nữ', '0');
				
				
INSERT INTO LOGIN_HISTORY(USER_ID,LOGIN_TIME)
VALUES
	(1, '2023-12-18 22:52:40'),
	(3, '2023-12-18 22:52:40'),
	( 3, '2023-12-18 22:52:40'),
	(1, '2023-12-18 22:52:40'),
	( 2, '2023-12-18 22:52:40'),
	( 6, '2023-12-18 22:52:40');

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
				
				

-- CHAT_HISTORY
INSERT INTO MESSAGE_USER (CHATBOX_ID,FROM_USER, TO_USER, TIME_SEND, CONTENT)
values
				('1-3',1, 3, '2023-12-18 22:52:40', 'Message1'),
				('2-3',2, 3, '2023-12-18 22:52:40', 'Message2'),
				('3-4',4, 3, '2023-12-18 22:52:40', 'Message4'),
				('1-3',3, 1, '2023-12-18 22:52:40', 'Message5'),
				('2-3',3, 2, '2023-12-18 22:52:40', 'Message6'),
				('4-6',4, 6, '2023-12-18 22:52:40', 'Message7'),
				('4-6',6, 4, '2023-12-18 22:52:40', 'Message8'),
				('1-9',9, 1, '2023-12-18 22:52:40', 'Message9');

-- GROUPCHAT
insert into GROUPCHAT (GROUP_NAME,CREATED_AT,ONLINE)
values
				('Group1', '2023-12-18 22:52:40','1'),
				('Group2', '2023-12-18 22:52:40','0'),
				('Group3', '2023-12-18 22:52:40','1');
-- GROUPCHAT MEMBER
insert into GROUPCHAT_MEMBER (GROUPCHAT_ID, MEMBER_ID,POSITION)
values
				(1, 1,'admin'),
				(1, 2,'member'),
				(1, 3,'member'),
				(2, 2,'admin'),
				(3, 3,'admin')		



