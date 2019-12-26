DROP TABLE comments;
drop sequence comments_seq;

CREATE TABLE comments(
	num	NUMBER PRIMARY KEY,
	id	VARCHAR2(15) references member2(id),
	content	VARCHAR2(200),
	reg_date 	date		NOT NULL,
	BOARD_RE_REF	NUMBER	references board(BOARD_NUM) on delete cascade
);

create sequence comments_seq ;

SELECT * FROM comments;