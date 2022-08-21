drop table if exists users cascade;
drop table if exists addresses;
drop table if exists warehouses cascade;
drop table if exists products cascade;
drop table if exists orders cascade;
drop table if exists order_products;

create table users (
	id varchar not null,
	username varchar not null,
	title varchar,
	fname varchar not null,
	password varchar not null,
	email varchar not null,
	hometown varchar null,
	admin bool not null,

	constraint pk_user_id
		primary key (id)
);

create table addresses (
	id varchar not null,
	full_name varchar not null,
	street1 varchar not null,
	street2 varchar,
	city varchar not null,
	region varchar not null,
	user_id varchar not null,

	constraint pk_address_id
		primary key (id),

	constraint fk_user_id
		foreign key (user_id) references users (id)
);

create table warehouses (
	id varchar not null,
	location varchar not null,
	stock_type varchar not null,

	constraint pk_warehouse_id
		primary key (id)
);

create table products (
	id varchar not null,
	item_name varchar not null,
	category varchar not null,
	price int not null,
	stock int not null,
	warehouse_id varchar not null,
	sort int not null,

	constraint pk_product_id
		primary key (id),

	constraint fk_warehouse_id
		foreign key (warehouse_id) references warehouses (id)
);

create table orders (
	id varchar not null,
	price int null,
	order_date date null,
	delivery_type varchar null,
	delivery_date varchar null,
	order_complete bool not null,
	user_id varchar not null,

	constraint pk_order_id
		primary key (id),

	constraint fk_user_id
		foreign key (user_id) references users (id)
);

create table order_products (
	id varchar not null,
	product_name varchar not null,
	product_price int not null,
	product_quantity int not null,
	order_id varchar not null,
	product_id varchar not null,

	constraint pk_orderProduct_id
		primary key (id),

	constraint fk_order_id
		foreign key (order_id) references orders (id),

	constraint fk_product_id
		foreign key (product_id) references products (id)
);

