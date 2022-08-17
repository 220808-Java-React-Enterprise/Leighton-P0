drop table if exists users cascade;
drop table if exists addresses cascade;
drop table if exists managers;
drop table if exists orders cascade;
drop table if exists products cascade;
drop table if exists regions;
drop table if exists stores cascade;


create table users (
    id varchar not null,
    username varchar not null,
    password varchar not null,
    email varchar not null,
    hometown varchar not null,

    constraint pk_users_id
        primary key (id),

    constraint fk_regions_id
        foreign key (regions_id) references regions (id),

);

create table addresses (
    id varchar not null,
    name varchar not null,
    street1 varchar not null,
    street2 varchar,
    city varchar not null,
    region varchar not null,

    constraint pk_addresses_id
        primary key (id),

    constraint fk_users_id
        foreign key (users_id) references users (id),
)

create table managers (
    id varchar not null,
    name varchar not null,
    employee_code varchar not null,
    password varchar not null,
    email varchar not null,

    constraint pk_managers_id
        primary key (id),
)

create table orders (
    id varchar not null,
    price varchar not null,
    order_date varchar not null,

    constraint pk_orders_id
        primary key (id),

    constraint fk_users_id
        foreign key (users_id) references users (id),

    constraint fk_stores_id
        foreign key (stores_id) references stores (id),
)

create table products (
    id varchar not null,
    name varchar not null,
    price varchar not null,
    stock varchar not null,

    constraint pk_products_id
        primary key (id),

    constraint fk_orders_id
        foreign key (orders_id) references orders (id),

    constraint fk_stores_id
        foreign key (stores_id) references stores (id),
)

create table regions (
    id varchar not null,
    name varchar not null,

    constraint pk_regions_id
        primary key (id),
)

create table stores (
    id varchar not null,
    number varchar not null,
    town varchar not null,

    constraint pk_stores_id
        primary key (id),

    constraint fk_managers_id
        foreign key (managers_id) references managers (id),

    constraint fk_regions_id
        foreign key (regions_id) references regions (id),
)