alter table topicos add column ativo boolean;
update topicos set ativo = true;
alter table topicos alter column ativo set not null;