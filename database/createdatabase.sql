create database shoppingcart;

create table item (
	item_id int not null auto_increment,
	item_name varchar(50) not null,
	carton_unit int not null,
	carton_price double not null,
    
	primary key(item_id)
);

insert into item values (null,'Penguin-ears',20,175.0);
insert into item values (null,'Horseshoe',5,825.0);