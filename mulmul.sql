# id - root
# pw - 1234

CREATE DATABASE mulmul CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

use mulmul;

show tables;

select * from member;
select * from follow;
delete from member where member_id = 1;

drop table member;

DESCRIBE member;
