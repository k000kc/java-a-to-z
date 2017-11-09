select c.name, t.name, e.name, cb.name  from car as c 
left outer join transmission as t on c.transmission_id = t.id
left outer join engine as e on c.engine_id = e.id
left outer join car_body as cb on c.car_body_id = cb.id;

select t.name from car as c
right outer join transmission as t on c.transmission_id = t.id where c.id is null;

select e.name from car as c
right outer join engine as e on c.engine_id = e.id where c.id is null;

select cb.name from car as c
right outer join car_body as cb on c.car_body_id = cb.id where c.id is null;