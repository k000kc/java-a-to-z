﻿insert into user_storage(nickname) values ('manager');
insert into user_info (fullname, phone, storage_id) values('Borisov Boris Borisovich', '+7(123)902-76-54', 3);
insert into roles(type_role) values('manager');
insert into user_role(user_id, role_id) values(3, 3);
insert into items_storage(user_id, name_item) values(3, 'Error 0x0000123');
insert into item_info(status, category, storage_id) values('РАССМОТРЕНИЕ','СРОЧНЫЙ', 3);
insert into comments_storage(symmary, item_id) values('тест', 1), ('рпоропропоп', 3); 
insert into comment(description, storage_id) values('this is description тест', 1), ('description too рпоропропоп', 3);
insert into file_storage(comment_id) values(3);
insert into file_info(filename, starage_id) values('d:\picture.jpg', 3);