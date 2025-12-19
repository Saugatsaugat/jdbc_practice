INSERT INTO users (name, email, joined_date) values('Hary Jr', 'haryjr@gmail.com', '2025-12-01');
INSERT INTO users (name, email, joined_date) values('Saugat Thapa', 'saugatthapa@gmail.com', '2024-12-01');
INSERT INTO users (name, email, joined_date) values('Natalia Sen', 'nataliasen@gmail.com', '2025-12-03');
INSERT INTO users (name, email, joined_date) values('John Kennedy', 'jk@gmail.com', '2025-12-02');
INSERT INTO users (name, email, joined_date) values('Lady Gaga', 'lg@gmail.com', '2025-11-02');

INSERT INTO books (title, author, category, quantity, added_date) values('Harry Potter', 'J.K. Rowling', 'Fantasy', 10, '2024-12-10');
INSERT INTO books (title, author, category, quantity, added_date) values('The Alchemist', 'Paulo Coelho', 'Fantasy', 10, '2024-12-10');
INSERT INTO books (title, author, category, quantity, added_date) values('Pride and Prejudice', 'Jane Austen', 'Literary', 10, '2024-12-15');
INSERT INTO books (title, author, category, quantity, added_date) values('Animal Farm', 'George Orwell', 'Influential', 10, '2024-12-01');
INSERT INTO books (title, author, category, quantity, added_date) values('Letting Go', 'Philip Roth', 'Fictional', 10, '2024-11-10');

INSERT INTO users_books (user_id, book_id, issue_date, due_date) values (1,1,'2025-12-01', '2025-12-08');
INSERT INTO users_books (user_id, book_id, issue_date, due_date) values (2,2,'2025-12-01', '2025-12-08');
INSERT INTO users_books (user_id, book_id, issue_date, due_date) values (3,3,'2025-12-03', '2025-12-10');
INSERT INTO users_books (user_id, book_id, issue_date, due_date) values (4,4,'2025-12-02', '2025-12-09');
INSERT INTO users_books (user_id, book_id, issue_date, due_date) values (5,5,'2025-12-01', '2025-12-08');

