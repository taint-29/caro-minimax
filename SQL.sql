--Create Database
create database test
go

--use database test
use test
go

--Create table nguoichoi
create table nguoichoi (
	Ten varchar(255),
	Thang int,
	Thua int
)
go
-- add data
insert into nguoichoi(Ten, Thang, Thua)
values ('gregr',1, 0),
('tai',5, 1),
('879',1, 1),
('56465',1, 1),
('lam',5, 1),
('Lam',5, 2),
('tai lam',3, 0),
('Quynh',5, 10),
('Hao',5, 0),
('harry',5, 8),
('messi',5, 8),
('ronaldo',5, 10),
('anh',2, 0),
('italia',2, 1),
('teayeon',10, 8)





