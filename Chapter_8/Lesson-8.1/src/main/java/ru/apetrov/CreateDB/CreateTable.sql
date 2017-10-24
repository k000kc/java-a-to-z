create table user_storage (
	id serial primary key,
	nickname character varying(20)
);

create table user_info (
	id serial primary key,
	fullname character varying(200),
	phone character(16),
	storage_id integer references user_storage(id)
);

create table roles (
	id serial primary key,
	type_role character varying(20)
);

create table user_role (
	user_id integer references user_info(id),
	role_id integer references roles(id)
);

create table items_storage (
	id serial primary key,
	user_id integer references user_info(id),
	name_item character varying(200)
);

create table item_info (
	id serial primary key,
	status character varying(20),
	category character varying(20),
	storage_id integer references items_storage(id)
);

create table comments_storage (
	id serial primary key,
	symmary character varying(200),
	item_id integer references item_info(id)
);

create table comment (
	id serial primary key,
	description text,
	storage_id integer references comments_storage(id)
);

create table file_storage (
	id serial primary key,
	comment_id integer references comment(id)
);

create table file_info (
	id serial primary key,
	filename character varying(50),
	starage_id integer references file_storage(id)
);