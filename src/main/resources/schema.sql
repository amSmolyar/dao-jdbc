create schema netology;

# создание таблицы customers

create table if NOT EXISTS customers
(
    id           int primary key auto_increment,
    name         varchar(100) not null,
    surname      varchar(100) not null,
    age          int check ( age > 0 AND age < 130 ),
    phone_number varchar(20)
);

insert into customers (name, surname, age, phone_number)
values ('Evgeniy', 'Smirnov', 31, '+79258745344');

insert into customers (name, surname, age, phone_number)
values ('Pavel', 'Ivanov', 37, '+79213456789');

insert into customers (name, surname, age, phone_number)
values ('Stepan', 'Petrov', 1, '+79031234567');

insert into customers (name, surname, age, phone_number)
values ('Olga', 'Alekseeva', 55, '+79019876543');

insert into customers (name, surname, age, phone_number)
values ('Mihail', 'Savinov', 48, '+79127654321');

insert into customers (name, surname, age, phone_number)
values ('Alexey', 'Bokov', 16, '+79127657654');

insert into customers (name, surname, age, phone_number)
values ('Alexey', 'Valeev', 42, '+79157837654');

update customers
set age = 27
where name = 'Stepan'
  AND surname = 'Petrov'
  AND phone_number = '+79031234567';


# создание таблицы orders

create table if NOT EXISTS orders
(
    id           int primary key auto_increment,
    date         timestamp    not null default now(),
    customer_id  int check ( customer_id > 0 ),
    product_name varchar(255) not null,
    amount       int check ( amount > 0 ),
    foreign key (customer_id) references customers (id)
);

insert into orders (customer_id, product_name, amount)
values (1, 'bread', 2);

insert into orders (customer_id, product_name, amount)
values (6, 'bread', 1);

insert into orders (customer_id, product_name, amount)
values (3, 'milk', 2);

insert into orders (customer_id, product_name, amount)
values (7, 'milk', 1);

insert into orders (customer_id, product_name, amount)
values (2, 'eggs', 3);

insert into orders (customer_id, product_name, amount)
values (4, 'eggs', 2);

insert into orders (customer_id, product_name, amount)
values (6, 'eggs', 1);

insert into orders (customer_id, product_name, amount)
values (7, 'socks-43', 2);

insert into orders (customer_id, product_name, amount)
values (5, 'socks-42', 3);

insert into orders (customer_id, product_name, amount)
values (1, 'socks-42', 3);

insert into orders (customer_id, product_name, amount)
values (1, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (2, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (3, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (4, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (5, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (6, 'champagne', 1);

insert into orders (customer_id, product_name, amount)
values (7, 'champagne', 1);