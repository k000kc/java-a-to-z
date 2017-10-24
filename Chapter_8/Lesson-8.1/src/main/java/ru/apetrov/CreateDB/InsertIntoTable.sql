insert into user_storage(nickname) values ('apetrov'), ('user');

insert into user_info (fullname, phone, storage_id) values('Petrov Andrey Anatolevich', '+7(123)456-78-90', 1);
insert into user_info (fullname, phone, storage_id) values('Testov Test Testovich', '+7(123)908-76-54', 2);

insert into roles(type_role) values('user'), ('admin');

insert into user_role(user_id, role_id) values(1, 1), (2, 2);

insert into items_storage(user_id, name_item) values(1, 'Error 404'), (2, 'Ничего не работает');

insert into item_info(status, category, storage_id) values('На выполнении', 'СРЕДНИЙ', 1), ('ВЫПОЛНЕН','НЕ СРОЧНЫЙ', 2);

insert into comments_storage(symmary, item_id) values('bla bla bla', 1), ('pam pam', 2); 

insert into comment(description, storage_id) values('this is description', 1), ('description too', 2);

insert into file_storage(comment_id) values(1), (2);

insert into file_info(filename, starage_id) values ('d:\dfdfdfd.txt', 1), ('d:\dfgdf.jpg', 2);