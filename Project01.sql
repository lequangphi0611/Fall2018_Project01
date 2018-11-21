

create database Phi_vinh_khai_Project01

go
	use Phi_vinh_khai_Project01
go

create table Employees(
	IdEmployees varchar(10) primary key not null,
	Name nvarchar(50) not null,
	Age int not null,
	Sex bit default 0,
	PhoneNumber varchar(11),
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

insert into Employees values('phi002',N'Lê Quang Phi',19,1,'0773927601',1,N'Thừa Thiên Huế')
go 
insert into Users values('admin','123','phi002')

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

create table Ware(
	Iditem int references Item(IdItem) on delete cascade unique,
	QuantityRemain int not null
)

go
create table Import(
	IdImport varchar(15) not null primary key,
	Iditem int references Item(IdItem) on delete no action,
	IdEmployees varchar(10) references Employees(IdEmployees) on delete no action, 
	TimeImport time default getdate(),
	DateImport date default getdate(),
	QuantityReceived int not null
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
			it.ItemName TenMatHang ,
			count(ip.QuantityReceived) SoLuongNhapVao,
			count(bd.Quantity) SoLuongBanDuoc,
			count(wh.QuantityRemain) SoLuongConTrongKho,
			sum(bd.Price * bd.Quantity) TongTienBanDuoc

		from Item it inner join BillDetail bd on it.IdItem = bd.IdItem
					inner join Import ip on it.IdItem = ip.IdItem
					inner join Ware wh on it.IdItem = wh.IdItem
		group by it.ItemName
	end
go



select * from Item it inner join BillDetail bd on it.IdItem = bd.IdItem
					inner join Import ip on it.IdItem = ip.Iditem
					inner join Ware wh on it.IdItem = wh.Iditem

---Bổ sung sp produce

create proc sp_SelectImportHistory @ItemID int
as 
	begin
		if @ItemID is null
			begin
				select
					ip.TimeImport, 
					ip.DateImport,
					ip.IdEmployees,
					ip.Iditem,
					ip.QuantityReceived				
				 from Import ip 
				 order by DateImport desc,TimeImport desc
			end
		else
			begin
				select
					ip.TimeImport, 
					ip.DateImport,
					ip.IdEmployees,
					ip.Iditem,
					ip.QuantityReceived				
				 from Import ip where ip.Iditem = @ItemID
				 order by DateImport desc,TimeImport desc
			end
	end
	go

