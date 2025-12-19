Create table users(
id int auto_increment,
name varchar(50) not null,
email varchar(50) unique not null,
joined_date date Not null,
constraint pk_users_id primary key(id)
);

Create table books(
id int auto_increment,
title varchar(50) NOT NULL,
author varchar(70) NOT NULL,
category varchar(50),
quantity int default 0,
added_date date,
constraint pk_books_id primary key(id)
);

create table users_books(
id int auto_increment,
user_id int NOT NULL,
book_id int NOT NULL,
issue_date date NOT NULL,
due_date date NOT NULL,
return_date date,
constraint pk_users_books_id PRIMARY KEY(id),
constraint fk_user_id FOREIGN KEY(user_id) references users(id),
constraint fk_books_id FOREIGN KEY(book_id) references books(id),
constraint chk_users_boook CHECK (due_date > issue_date)
);
