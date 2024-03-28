drop table if exists public.users;

create table if not exists public.users
(
    username varchar(128) primary key,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    role varchar(32)
);