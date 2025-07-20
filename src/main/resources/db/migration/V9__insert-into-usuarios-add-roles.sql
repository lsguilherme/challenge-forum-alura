insert into usuarios_roles (user_id, role_id)
values (
    (select id from usuarios where login = 'teste'),
    (select role_id from role where name = 'ROLE_USER')
);