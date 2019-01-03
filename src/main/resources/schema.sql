drop table pacient if exists;
create table pacient (id varchar(36) not null, active boolean, created_at timestamp, email varchar(30) not null, password varchar(15) not null, register_confirmed boolean, reset_password boolean, birth_date date, gender varchar(8), primary key (id));
alter table pacient add constraint UK_r4tslbv0lk9lqi95jq6xrmkec unique (email);