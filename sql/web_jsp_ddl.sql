CREATE TABLE IF NOT EXISTS  member (
	user_seq 		int				PRIMARY KEY	auto_increment,
	user_id 		varchar(10)		NOT NULL unique,
	user_password 	varchar(65)		NOT NULL,
	user_name 		varchar(10)		NOT NULL,
	user_nickname	varchar(10)		NOT NULL unique,
	user_email		varchar(50)		NOT NULL unique,
	created_at		datetime(6)		NOT NULL,
	user_profile	varchar(200)
);

CREATE TABLE IF NOT EXISTS  board (
	brd_seq			int			PRIMARY KEY	auto_increment,
	brd_title		varchar(45)	NOT NULL,
	brd_content		text		NOT NULL,
	created_at		datetime(6)	NOT NULL,
	modified_at		datetime(6),
	brd_view_count	int			DEFAULT 0,
	brd_like_count	int			DEFAULT 0,
	brd_cmt_count	int			DEFAULT 0,
	user_seq		int			NOT NULL,	
	CONSTRAINT fk_board_member FOREIGN KEY (user_seq) REFERENCES member(user_seq)
);


CREATE TABLE IF NOT EXISTS  board_like (
	brd_seq		int	NOT NULL,
	user_seq	int	NOT NULL,
	PRIMARY KEY (brd_seq, user_seq),
	CONSTRAINT fk_board_like_board FOREIGN KEY (brd_seq) REFERENCES board(brd_seq),
	CONSTRAINT fk_board_like_member FOREIGN KEY (user_seq) REFERENCES member(user_seq)
	
);

CREATE TABLE IF NOT EXISTS comment (
	cmt_seq			int				PRIMARY KEY	auto_increment,
	cmt_content		varchar(2000)	NOT NULL,
	created_at		datetime(6)		NOT NULL,
	modified_at		datetime(6)		NULL,
	cmt_like_count	int	NULL		DEFAULT 0,
	cmt_parent_seq	int	NULL,
	cmt_is_deleted	bit				NULL	DEFAULT false,
	brd_seq			int				NOT NULL,
	user_seq		int				NOT NULL,
	CONSTRAINT fk_comment_board FOREIGN KEY (brd_seq) REFERENCES board(brd_seq),
	CONSTRAINT fk_comment_member FOREIGN KEY (user_seq) REFERENCES member(user_seq)
);

CREATE TABLE IF NOT EXISTS  comment_like (
	user_seq	int	NOT NULL,
	cmt_seq		int	NOT NULL,
	PRIMARY KEY (user_seq, cmt_seq),
	CONSTRAINT fk_comment_like_member FOREIGN KEY (user_seq) REFERENCES member(user_seq),
	CONSTRAINT fk_comment_like_comment FOREIGN KEY (cmt_seq) REFERENCES comment(cmt_seq)
);

CREATE FULLTEXT INDEX idx_ft_board_title_and_content ON board (brd_title, brd_content);


insert into member(user_id, user_password, user_name, user_nickname, user_email, created_at)values('testId', 'testpassword', 'testName', 'testNick', 'test@email', now());
