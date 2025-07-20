alter table usuarios add column is_account_non_expired boolean;
update usuarios set is_account_non_expired = true;
alter table usuarios alter column is_account_non_expired set not null;

alter table usuarios add column is_credentials_non_expired boolean;
update usuarios set is_credentials_non_expired = true;
alter table usuarios alter column is_credentials_non_expired set not null;

alter table usuarios add column is_enabled boolean;
update usuarios set is_enabled = true;
alter table usuarios alter column is_enabled set not null;

alter table usuarios add column is_account_non_locked boolean;
update usuarios set is_account_non_locked = true;
alter table usuarios alter column is_account_non_locked set not null;