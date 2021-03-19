--PostgreSQL

CREATE SCHEMA cinema
    AUTHORIZATION postgres;

set search_path to cinema;

drop table if exists sold_tickets;
drop table if exists sessions;
drop table if exists films;

create table films (
	id bigserial PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	duration integer NOT NULL
);

create table sessions(
	id bigserial PRIMARY KEY,
	film_id bigint NOT NULL REFERENCES films(id),
	startDate timestamp NOT NULL,
	price decimal NOT NULL

);

create table sold_tickets(
	id bigserial PRIMARY KEY,
	session_id bigint NOT NULL REFERENCES sessions(id)
);

insert into films (name, duration)
values
('Godfather', 180),
('The Hitcher', 90),
('Blade Runner', 120),
('Commandos', 90);

insert into sessions (film_id, startDate, price)
values
(1, '2020-02-13 9:00:00', 50),
(2, '2020-02-13 11:30:00', 70),
(1, '2020-02-13 13:30:00', 100),
(2, '2020-02-13 17:00:00', 120),
(4, '2020-02-13 18:25:00', 150),
(1, '2020-02-14 9:00:00', 60),
(2, '2020-02-14 12:00:00', 80),
(1, '2020-02-14 13:30:00', 110),
(3, '2020-02-14 17:30:00', 130),
(4, '2020-02-14 21:00:00', 160);

insert into sold_tickets (session_id)
values
(1),
(1),
(1),
(1),
(2),
(2),
(3),
(4),
(4),
(4),
(4),
(4),
(5),
(5),
(5),
(5),
(5),
(5),
(5),
(6),
(7),
(7),
(8),
(8),
(8),
(8),
(8),
(9),
(10),
(10),
(10),
(10),
(10),
(10);

