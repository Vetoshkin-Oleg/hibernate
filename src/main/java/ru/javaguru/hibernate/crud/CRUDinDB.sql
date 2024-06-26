drop table if exists public.company cascade;
drop table if exists public.users cascade;
drop table if exists public.profile cascade;
drop table if exists public.chat cascade;
drop table if exists public.users_chat cascade;
drop table if exists public.programmer cascade;
drop table if exists public.manager cascade;
drop table if exists public.payment cascade;

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

create table if not exists public.chat
(
    id bigserial primary key,
    name varchar(64) not null unique
);

create table if not exists public.users_chat
(
    id bigserial primary key,
    user_id bigint references public.users(id),
    chat_id bigint references public.chat(id),
    created_at timestamp not null,
    created_by varchar(128) not null
);

create table if not exists public.payment
(
    id bigserial primary key,
    amount serial not null,
    version bigserial,
    receiver_id int references users(id)
);

create sequence users_id_seq
    owned by public.users.id;