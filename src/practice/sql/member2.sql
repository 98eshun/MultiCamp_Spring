create table member2(
	 id		  	number(7) 	 	constraint member2_id_pk primary key
,	 name	 	varchar(20)  	constraint member2_name_nn not null
,	 email	  	varchar(20)  	constraint member2_email_nn not null
, 	 password 	varchar(20)  	constraint member2_password_nn not null
, 	 mkdate  		date	  	 	default sysdate
,	 constraint member2_email_uq unique (email)
);

create sequence member2_id_seq
	start with 1
	increment by 1
	maxvalue 9999999
	nocycle;

insert into member2 (id, name, email, password)
values (member2_id_seq.nextval, '¿ÃΩ¬»∆', 'lee@naver.com', '1234');

insert into member2 (id, name, email, password)
values (member2_id_seq.nextval, '±Ë»£µø', 'kim@naver.com', '2345');

commit;

select * from member2;