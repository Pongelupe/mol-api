drop table doctor if exists
drop table pacient if exists
create table doctor (id varchar(36) not null, active boolean, birth_date date, confirmed boolean, cpf varchar(13) not null, created_at timestamp, email varchar(30) not null, gender varchar(8), password varchar(15) not null, phone varchar(50), reset_password boolean, updated_at timestamp, crm varchar(30), crm_file varchar(255), field varchar(50), rg varchar(20), state varchar(2), primary key (id))
create table pacient (id varchar(36) not null, active boolean, birth_date date, confirmed boolean, cpf varchar(13) not null, created_at timestamp, email varchar(30) not null, gender varchar(8), password varchar(15) not null, phone varchar(50), reset_password boolean, updated_at timestamp, primary key (id))
alter table doctor add constraint UK_DOCTOR_CPF unique (cpf)
alter table doctor add constraint UK_DOCTOR_EMAIL unique (email)
alter table pacient add constraint UK_PACIENT_CPF unique (cpf)
alter table pacient add constraint UK_PACIENT_EMAIL unique (email)