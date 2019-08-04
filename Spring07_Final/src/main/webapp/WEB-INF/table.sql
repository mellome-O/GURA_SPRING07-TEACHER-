CREATE TABLE board_file(
num NUMBER PRIMARY KEY, -- 파일번호
writer VARCHAR2(100), -- 작성자
title VARCHAR2(100), -- 제목
orgFileName VARCHAR2(100), -- 원본파일명
saveFileName VARCHAR2(100), -- 파일 시스템에 저장된 파일명
fileSize NUMBER, -- 파일의 크기(byte)
downCount NUMBER DEFAULT 0, -- 다운로드 횟수
regdate DATE -- 등록일 
);

CREATE SEQUENCE board_file_seq;

CREATE TABLE board_gallery(
num NUMBER PRIMARY KEY,
writer VARCHAR2(100),
caption VARCHAR2(200),
imagePath VARCHAR2(100),
regdate DATE
);

CREATE SEQUENCE board_gallery_seq;

CREATE TABLE users
(id VARCHAR2(100) PRIMARY KEY,
pwd VARCHAR2(100) NOT NULL,
email VARCHAR2(100),
regdate DATE,
profile VARCHAR2(100));


CREATE TABLE board_cafe(
num NUMBER PRIMARY KEY,
writer VARCHAR2(100) NOT NULL,
title VARCHAR2(100) NOT NULL,
content CLOB,
viewCount NUMBER, 
regdate DATE
);

CREATE SEQUENCE board_cafe_seq;


-- 댓글 정보를 저장할 테이블
CREATE TABLE board_cafe_comment(
	num NUMBER PRIMARY KEY, -- 댓글의 글번호
	writer VARCHAR2(100), -- 댓글 작성자
	content VARCHAR2(500), -- 댓글 내용
	target_id VARCHAR2(100), -- 댓글의대상이되는아이디(글작성자)
	ref_group NUMBER, -- 댓글 그룹번호
	comment_group NUMBER, -- 원글에 달린 댓글 내에서의 그룹번호
	deleted CHAR(3) DEFAULT 'no', -- 댓글이 삭제 되었는지 여부 
	regdate DATE -- 댓글 등록일 
);

CREATE SEQUENCE board_cafe_comment_seq;

CREATE TABLE users
(id VARCHAR2(100) PRIMARY KEY,
pwd VARCHAR2(100) NOT NULL,
email VARCHAR2(100),
regdate DATE,
profile VARCHAR2(100));


-- 상품 테이블
CREATE TABLE shop(
	num NUMBER PRIMARY KEY, --상품번호
	name VARCHAR2(30), --상품이름
	price NUMBER, --상품가격
	remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 
);

-- 고객 계좌 테이블
CREATE TABLE client_account(
	id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
	money NUMBER CHECK(money >= 0), -- 고객의 잔고 
	point NUMBER
);

-- 주문 테이블
CREATE TABLE client_order(
	num NUMBER PRIMARY KEY, -- 주문번호
	id VARCHAR2(30), -- 주문 고객의 아이디
	code NUMBER, -- 주문한 상품의 번호 
	addr VARCHAR2(50) -- 배송 주소
);

-- 주문 테이블에 사용할 시퀀스 
CREATE SEQUENCE client_order_seq;

DELETE FROM shop;
DELETE FROM client_account;
DELETE FROM client_order;

-- sample 데이터
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', '1000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', '2000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', '3000', 5);

INSERT INTO client_account (id, money, point)
VALUES('superman', 10000, 0);

INSERT INTO client_account (id, money, point)
VALUES('batman', 10000, 0);




