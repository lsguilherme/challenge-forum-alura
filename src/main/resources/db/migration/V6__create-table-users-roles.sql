create table usuarios_roles(
    user_id bigint not null,
    role_id integer not null,
    primary key(user_id, role_id),
    foreign key (user_id) references usuarios(id),
    foreign key (role_id) references role(role_id)
);