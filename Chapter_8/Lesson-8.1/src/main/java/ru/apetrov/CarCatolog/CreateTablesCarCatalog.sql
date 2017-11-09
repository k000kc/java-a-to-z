create table transmission(
	id serial primary key,
	name character varying(20)
);

create table engine(
	id serial primary key,
	name character varying(20)
);

create table car_body(
	id serial primary key,
	name character varying(20)
);

create table car (
	id serial primary key,
	name character varying(20),
	transmission_id integer references transmission(id),
	engine_id integer references engine(id),
	car_body_id integer references car_body(id)
);