drop database if exists `watch_store_db`;
create database if not exists watch_store_db;

use watch_store_db;

create table manufacturers (
    manufacturer_id int auto_increment primary key,
    name varchar(150) not null,
    country varchar(100) not null
);

create table watches(
    watch_id int auto_increment primary key,
    brand varchar(100) not null,
    type enum('quartz', 'mechanical') default 'mechanical',
    price decimal (10, 2) not null default 0.00,
    quantity int not null default 0,
    manufacturer_id int not null,
    constraint fk_watches_manufacturers foreign key (manufacturer_id) references manufacturers (manufacturer_id)
        on delete cascade
);