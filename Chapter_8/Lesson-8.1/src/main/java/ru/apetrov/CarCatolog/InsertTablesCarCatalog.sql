insert into transmission(name) values('автоматическая'), ('полуавтоматическая'), ('гидроавтоматическая'), ('безступенчатая'), ('ручная');

insert into engine(name) values('гибридный'), ('бензиновый'), ('дизельный'), ('роторный');

insert into car_body(name) values('седан'), ('универсал'), ('фургон'), ('хэтчбэк'), ('купэ'), ('кабриолет'), ('лимузин'), ('пикап'), ('минивэн'), ('кроссовер');

insert into car(name, transmission_id, engine_id, car_body_id) values('first-car', 1, 4, 6);

insert into car(name, transmission_id, engine_id, car_body_id) values('second-car', 3, 2, 4), ('third_car', 5, 1, 10);