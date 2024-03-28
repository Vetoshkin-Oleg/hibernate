drop table if exists public.users;

create table if not exists public.users
(
    id serial primary key,
    username varchar(128) unique,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    role varchar(32)
);

create sequence users_id_seq
    owned by public.users.id;