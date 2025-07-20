create table role (
    role_id serial primary key,
    name text not null unique
);