create database project01

go
	use project01
go

create table Employees(
	IdEmployees varchar(5) primary key not null,
	Name nvarchar(50) not null,
	Age int not null,
	Role bit default 0
)


go



create table Users(
	UserName varchar (5) primary key not null,
	Pass varchar(16) not null,
	IdEmployees varchar(5) references Employees(IdEmployees) on Delete cascade unique
)

go

create table Category(
	IdCategory int identity(1,1) primary key not null,
	CategoryName nvarchar(30) unique not null
)

go 

create table Item(
	IdItem int identity(1,1) not null,
	ItemName nvarchar(30) unique not null,
	Price money not null,
	IdCategory int references Category(IdCategory) on delete set null
)

go

create table Content(
	IdContent varchar(7) primary key not null,
	TableNum int not null,
	Content nvarchar(max)
)

go

create table bill(
	IdBill varchar(5) primary key not null,
	IdContent varchar(7) references Content(IdContent)on delete cascade,
	IdEmloyees varchar(5) references Employees(IdEmployees),
	DatePayment Date default getDate(),
	SumPrice money not null
)

go 


