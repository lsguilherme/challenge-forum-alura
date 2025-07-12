create table topicos(
    id bigserial primary key,
    titulo varchar(255) not null unique,
    mensagem text not null,
    criado_em timestamp not null default current_timestamp,
    estado varchar(50) not null,
    autor varchar(255) not null,
    curso varchar(255) not null
);