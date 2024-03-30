drop table if exists public.company cascade;
drop table if exists public.users cascade;
drop table if exists public.profile cascade;

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
    company_id int references company(id)
);

create table if not exists public.profile
(
    id bigserial primary key,
    user_id bigint not null unique references public.users(id),
    street varchar(128),
    language char(2)
);

/*create table if not exists public.profile
(
    user_id bigint primary key references public.users(id),
    street varchar(128),
    language char(2)
);*/

create sequence users_id_seq
    owned by public.users.id;