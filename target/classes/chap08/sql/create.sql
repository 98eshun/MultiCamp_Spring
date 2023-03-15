create table member(
	id	 		number(7)	 	constraint member_id_pk primary key
,	email 		varchar2(20) 	constraint member_email_nn	not null
,	password 	varchar2(20) 	constraint member_password_nn not null
,	name 		varchar2(20) 	constraint member_ename_nn not null
,	regdate	 	date 			default sysdate
,	constraint member_email_uq unique (email)
);

create sequence member_id_seq
	start with 1
	increment by 1
	maxvalue 9999999
	nocycle;
	
insert into member (id, email, password, name)
values(member_id_seq.nextval, 'lonlee@daum.net', '6789', '¿Ãº∫±∏' );

insert into member (id, email, password, name)
values(member_id_seq.nextval, 'hmson@naver.com', '1234', 'º’»ÔπŒ' );

commit;

select * from member;