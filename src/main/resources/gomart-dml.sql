insert into warehouses (id, location, stock_type)
values ('WH001', 'Vermillion City', 'Medicines');
insert into warehouses (id, location, stock_type)
values ('WH002', 'Saffron City', 'Pokeballs');
insert into warehouses (id, location, stock_type)
values ('WH003', 'Celadon City', 'Field Items');

insert into products (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M001', 'Potion', 'P', 300, 1000, 1000, 'WH001', 1);
insert into products (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M002', 'Super Potion', 'P', 700, 500, 500, 'WH001', 2);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M003', 'Hyper Potion', 'P', 1200, 300, 300, 'WH001', 3);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M004', 'Max Potion', 'P', 2500, 100, 100, 'WH001', 4);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M011', 'Full Restore', 'P', 3000, 100, 100, 'WH001', 5);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M010', 'Antidote', 'M', 100, 1000, 1000, 'WH001', 6);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M020', 'Paralyze Heal', 'M', 200, 1000, 1000, 'WH001', 7);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M030', 'Awakening', 'M', 250, 1000, 1000, 'WH001', 8);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M040', 'Burn Heal', 'M', 250, 1000, 1000, 'WH001', 9);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M050', 'Ice Heal', 'M', 250, 1000, 1000, 'WH001', 10);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M110', 'Full Heal', 'M', 600, 200, 200, 'WH001', 11);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('M100', 'Revive', 'M', 1500, 100, 100, 'WH001', 12);

insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('B001', 'Poke Ball', 'B', 200, 5000, 5000, 'WH002', 13);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('B010', 'Great Ball', 'B', 600, 2500, 2500, 'WH002', 14);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('B100', 'Ultra Ball', 'B', 1200, 1000, 1000, 'WH002', 15);

insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('F001', 'Repel', 'F', 350, 2000, 2000, 'WH003', 16);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('F002', 'Super Repel', 'F', 500, 1000, 1000, 'WH003', 17);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('F003', 'Max Repel', 'F', 700, 500, 500, 'WH003', 18);
insert into products  (id, item_name, category, price, stock, max_stock, warehouse_id, sort)
values ('F111', 'Escape Rope', 'F', 550, 300, 300, 'WH003', 19);

insert into users (id, username, title, fname, password, email, hometown, admin)
values ('001', 'admin','admin', 'admin', 'admin', 'admin', 'admin', true);
insert into users (id, username, title, fname, password, email, hometown, admin)
values ('u01', 'User001','User', 'User', 'P@ssw0rd', 'user1@pokemon.com', 'Pallet Town', false);
insert into users (id, username, title, fname, password, email, hometown, admin)
values ('u02', 'User002','User', 'User', 'P@ssw0rd', 'user2@pokemon.com', 'Viridian City', false);
insert into users (id, username, title, fname, password, email, hometown, admin)
values ('u03', 'User003','User', 'User', 'P@ssw0rd', 'user3@pokemon.com', 'Pewter City', false);
insert into users (id, username, title, fname, password, email, hometown, admin)
values ('u04', 'User004','User', 'User', 'P@ssw0rd', 'user4@pokemon.com', 'Cerulean City', false);
insert into users (id, username, title, fname, password, email, hometown, admin)
values ('u05', 'User005','User', 'User', 'P@ssw0rd', 'user5@pokemon.com', 'Lavender Town', false);

insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('admin001', 2000, '2021-04-02', 'Expedited', '2021-04-02', true, '001');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('admin002', 3000, '2021-03-02', 'Expedited', '2021-03-02', true, '001');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('admin003', 7000, '2022-04-02', 'Expedited', '2022-04-02', true, '001');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('admin004', 1000, '2020-04-02', 'Expedited', '2020-04-02', true, '001');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('user101', 2000, '2021-04-02', 'Expedited', '2021-04-02', true, 'u01');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('user102', 3000, '2021-03-02', 'Expedited', '2021-03-02', true, 'u01');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('user103', 7000, '2022-04-02', 'Expedited', '2022-04-02', true, 'u01');
insert into orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id)
values ('user104', 1000, '2020-04-02', 'Expedited', '2020-04-02', true, 'u01');

insert into addresses (id, full_name, street, city, region, user_id)
values ('admin', 'admin', '123 Admin Street', 'Pallet Town', 'Kanto', '001')
