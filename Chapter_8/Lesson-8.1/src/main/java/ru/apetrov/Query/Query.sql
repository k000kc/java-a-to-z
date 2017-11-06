select nickname, name_item from user_storage as us
inner join items_storage as its on us.id = its.user_id;

select fullname, phone, name_item, symmary from user_info as ui
inner join items_storage as its on ui.id = its.user_id
inner join comments_storage as cs on its.id = cs.item_id and cs.symmary = 'тест';

select nickname, name_item from user_storage as us
inner join items_storage as its on us.id = its.user_id and its.name_item like 'Error%';

select fullname, phone, name_item, symmary, description from user_info as ui
inner join items_storage as its on ui.id = its.user_id
inner join comments_storage as cs on its.id = cs.item_id
inner join comment_info as c on c.storage_id = cs.id;