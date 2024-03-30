drop table if exists public.company cascade;
drop table if exists public.users cascade;

create table if not exists public.company
(
    id serial primary key,
    name varchar(64) not null unique
);

create table if not exists public.users
(
    id bigserial primary key,
    username varchar(128) unique,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    role varchar(32),
    company_id int references company
);

create sequence users_id_seq
    owned by public.users.id;