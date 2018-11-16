

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
	IdCategory varchar(6) primary key not null,
	CategoryName nvarchar(30) unique not null
)

go 

create table Item(
	IdItem int identity(1,1) not null primary key,
	ItemName nvarchar(30) unique not null,
	Unit nvarchar(20) not null,
	Price money not null,
	IdCategory varchar(6) references Category(IdCategory) on delete set null,
	isSell bit default 1
)

go

create table bill(
	IdBill varchar(10) primary key not null,
	IdEmployees varchar(10) references Employees(IdEmployees) on delete no action,
	TimePayment Time default getDate(),
	DatePayment Date default getDate(),
	TableNumber int not null,
	SumPrice money not null,
	Sale money default 0,
	Total money not null
)

go 


create table BillDetail(
	IdBill varchar(10) references bill(IdBill) on delete cascade,
	IdItem int references Item(IdItem) on update no action on delete cascade,
	Quantity int not null,
	Price money not null
)

go



create proc sp_LichSuGiaoDich @minDate date, @maxDate date
as 
	select * from bill 
	where 
		bill.DatePayment >= @minDate and bill.DatePayment <= @maxDate 
	order by DatePayment desc, TimePayment desc
go



create proc sp_ThongKeDoanhThuTheoNgay 
as
	begin
		select 
			b.DatePayment times,
			count(b.IdBill) SoluongGiaoDich,
			min(b.total) DoanhThuThapNhat,
			max(b.total) DoanhThuCaoNhat,
			AVG(b.Total) DoanhThuTrungBinhMoiGiaoDich,
			SUM(b.Total) TongDoanhThu
		from bill b group by b.DatePayment
	end
go

create proc sp_ThongKeDoanhThuTheoThang
as
	begin
		select
			month(b.DatePayment) Thang,
			year(b.DatePayment) Nam,
			count(b.IdBill) SoluongGiaoDich,
			min(b.total) DoanhThuThapNhat,
			max(b.total) DoanhThuCaoNhat,
			AVG(b.Total) DoanhThuTrungBinhMoiGiaoDich,
			SUM(b.ToTal) TongDoanhThu
			
		from bill b group by month(b.DatePayment),year(b.DatePayment)
	end
go

create proc sp_ThongKeDoanhThuTheoNam
as
	begin
			select
				year(b.DatePayment) times,
				count(b.IdBill) SoluongGiaoDich,
				min(b.total) DoanhThuThapNhat,
				max(b.total) DoanhThuCaoNhat,
				AVG(b.Total) DoanhThuTrungBinhMoiGiaoDich,
				SUM(b.ToTal) TongDoanhThu
			
			from bill b group by year(b.DatePayment)
	end
go

create proc sp_ThongKeMatHangBanDuoc
as
	begin
		select 
			item.ItemName TenMatHang,
			SUM(BD.Quantity) TongSoLuongBanDuoc,
			Min((item.Price * BD.Quantity)) BanDuocItNhat,
			max((item.Price * BD.Quantity)) BanDuocNhieuNhat,
			SUM((item.Price * BD.Quantity)) TongTien
		from BillDetail BD 
				inner join Item item on BD.IdItem = item.IdItem
		group by item.ItemName, Item.Unit order by TongSoLuongBanDuoc desc
	end
go


