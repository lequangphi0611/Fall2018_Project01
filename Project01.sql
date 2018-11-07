create database project01

go
	use project01
go

create table Employees(
	IdEmployees varchar(10) primary key not null,
	Name nvarchar(50) not null,
	Age int not null,
	Sex bit default 0,
	PhoneNumber varchar(11) unique,
	Role bit default 0,
	Address nvarchar(255) not null
)


go

create table Users(
	UserName varchar (10) primary key not null,
	Pass varchar(20) not null,
	IdEmployees varchar(10) references Employees(IdEmployees) on Delete cascade unique
)

go

create table Category(
	IdCategory varchar(5) primary key not null,
	CategoryName nvarchar(30) unique not null
)

go 

create table Item(
	IdItem int identity(1,1) not null primary key,
	ItemName nvarchar(30) unique not null,
	Price money not null,
	IdCategory varchar(5) references Category(IdCategory) on delete set null
)

go

create table bill(
	IdBill varchar(5) primary key not null,
	IdEmloyees varchar(10) references Employees(IdEmployees),
	DatePayment Date default getDate(),
	TableNumber int not null,
	SumPrice money not null
)

go 


create table BillDetail(
	IdBill varchar(5) references bill(IdBill) on delete cascade unique ,
	IdItem int references Item(IdItem) on update no action,
	Quantity int not null,
	Price money not null
)

go








