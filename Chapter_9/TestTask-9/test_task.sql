create table if not exists roles(
	id integer primary key unique, 
	role character varying(30) unique
);
insert into roles(id, role) values(1, 'user'),(2, 'mandator'), (3, 'admin');

create table if not exists address(
	id SERIAL PRIMARY KEY, 
	country CHARACTER VARYING(50), 
	city CHARACTER VARYING(50), 
	street CHARACTER VARYING(50), 
	house CHARACTER VARYING(50)
);

insert into address(id, country, city, street, house) values(1, 'Russia', 'Moscow', 'RRR', '1212');

create table if not exists musics(
	id integer primary key unique, 
	music_type character varying(50)
);
insert into musics(id, music_type) values(1, 'rap'), (2, 'rock'), (3, 'pop');

CREATE TABLE IF NOT EXISTS users(
	login CHARACTER VARYING(30) UNIQUE PRIMARY KEY, 
	password CHARACTER VARYING(30), 
	user_name CHARACTER VARYING(50), 
	email CHARACTER VARYING(50), 
	address_id integer references address(id) unique, 
	role_id INTEGER REFERENCES roles(id)
);

insert into users(login, password, user_name, email, address_id, role_id) values('root', 'root', 'root', 'root@mail.ru', 1, 3);

create table login_music_id(
	user_login CHARACTER VARYING(30) REFERENCES users(login),
	music_id integer REFERENCES musics(id),
	primary key (user_login, music_id)
);

insert into login_music_id(user_login, music_id) values('root', 2);
