# id - root
# pw - 1234

CREATE DATABASE mulmul CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use mulmul;

show tables;

select * from categoryvo;


select * from user;

show variables like 'c%';
SELECT VERSION();

drop database mulmul;