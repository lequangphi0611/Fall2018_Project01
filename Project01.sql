create database project01

go

create table typeItem(
	Id char(3) primary key not null,
	TypeItemName nvarchar(50) not null
)

go

create table item(
	Id int identity(1,1) primary key not null,
	ItemName nvarchar(50) unique not null,
	TypeItemID char(3) not null references typeItem(Id) on Delete set null,
	Price money not null,
	isSpecial bit default 0
)

go

create table bill(
	Id int identity(1,1) primary key not null,
	Contents nvarchar(max) not null,
	DatePayment Date default getDate(),
	SumPrice money not null
)

go 

create table employees()