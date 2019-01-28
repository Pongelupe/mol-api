drop table active_princible if exists
drop table administration_form if exists
drop table atc if exists
drop table contraindication if exists
drop table contraindication_medicines if exists
drop table doctor if exists
drop table medicine if exists
drop table medicine_active_princible if exists
drop table medicine_administration_form if exists
drop table medicine_pharmaceutical_form if exists
drop table patient if exists
drop table pharmaceutical_form if exists
drop table prescription if exists
drop table prescription_prescription_items if exists
drop table prescription_item if exists
drop table regulatory_category if exists
drop table therapeutical_class if exists
create table active_princible (id varchar(36) not null, description varchar(80), primary key (id))
create table administration_form (id varchar(36) not null, description varchar(80), primary key (id))
create table atc (id varchar(36) not null, description varchar(80), primary key (id))
create table contraindication (id varchar(36) not null, description varchar(200), patient_id varchar(36) not null, primary key (id))
create table contraindication_medicines (contraindication_id varchar(36) not null, medicines_id varchar(36) not null, primary key (contraindication_id, medicines_id))
create table doctor (id varchar(36) not null, active boolean, birth_date date, confirmed boolean, cpf varchar(14) not null, created_at timestamp, email varchar(30) not null, gender varchar(8), name varchar(50) not null, password varchar(60) not null, phone varchar(50), reset_password boolean, updated_at timestamp, crm varchar(30), digital_signature blob not null, field varchar(50), rg varchar(20), state varchar(2), primary key (id))
create table medicine (id varchar(36) not null, comercial_name varchar(90) not null, company_name varchar(80), expiration integer, fractionable boolean, intended_for integer, measure_type varchar(20), monodrug boolean, prescription_restriction varchar(255), presentation_diff_complement varchar(255), preservation varchar(255), public_report varchar(255), record_number bigint, record_publication date, record_status varchar(255), tarja varchar(255), atc_id varchar(36), medicine_of_reference_id varchar(36), regulatory_category_id varchar(36), therapeutical_class_id varchar(36), primary key (id))
create table medicine_active_princible (medicine_id varchar(36) not null, active_princible_id varchar(36) not null, primary key (medicine_id, active_princible_id))
create table medicine_administration_form (medicine_id varchar(36) not null, administration_form_id varchar(36) not null, primary key (medicine_id, administration_form_id))
create table medicine_pharmaceutical_form (medicine_id varchar(36) not null, pharmaceutical_form_id varchar(36) not null, primary key (medicine_id, pharmaceutical_form_id))
create table patient (id varchar(36) not null, active boolean, birth_date date, confirmed boolean, cpf varchar(14) not null, created_at timestamp, email varchar(30) not null, gender varchar(8), name varchar(50) not null, password varchar(60) not null, phone varchar(50), reset_password boolean, updated_at timestamp, primary key (id))
create table pharmaceutical_form (id varchar(36) not null, description varchar(80), primary key (id))
create table prescription (id varchar(36) not null, created_at timestamp, doctor_id varchar(255), observation varchar(200), patient_id varchar(255), prescription_type integer, shelf_life date, primary key (id))
create table prescription_prescription_items (prescription_id varchar(36) not null, prescription_items_id varchar(36) not null, primary key (prescription_id, prescription_items_id))
create table prescription_item (id varchar(36) not null, description varchar(200), medicine_id varchar(255), quantity double, prescription_id varchar(36), primary key (id))
create table regulatory_category (id varchar(36) not null, description varchar(80), primary key (id))
create table therapeutical_class (id varchar(36) not null, description varchar(80), primary key (id))
alter table doctor add constraint UK_qync0awn93kto0lhbikmpvolw unique (cpf)
alter table doctor add constraint UK_jdtgexk368pq6d2yb3neec59d unique (email)
alter table doctor add constraint UK_rm7bun68aaxihobddw7oex38h unique (crm)
alter table doctor add constraint UK_jfk1hmalhldrh6ccn1sk5qdrs unique (rg)
alter table medicine_administration_form add constraint UK_3v5qrc8vsqskspxccn0qub4ta unique (administration_form_id)
alter table patient add constraint UK_bhyw8ppxv52lm9q86kf6gsy40 unique (cpf)
alter table patient add constraint UK_bawli8xm92f30ei6x9p3h8eju unique (email)
alter table prescription_prescription_items add constraint UK_bud9fsgaxmnvg6ihqhd97k4vk unique (prescription_items_id)
alter table contraindication add constraint FKouhf1rx2n10o9yvquyvgpewh3 foreign key (patient_id) references patient
alter table contraindication_medicines add constraint FKk4bjix2l66vky53e4y6pufx5q foreign key (medicines_id) references medicine
alter table contraindication_medicines add constraint FKfcjakkenjf5fupck22vb8x40c foreign key (contraindication_id) references contraindication
alter table medicine add constraint FK766h77spfboeh8tjopcv5lwgk foreign key (atc_id) references atc
alter table medicine add constraint FKg8dbdiork3isf1j5i45tu2ilm foreign key (medicine_of_reference_id) references medicine
alter table medicine add constraint FK1w9xadkfxb9weipsiuqpbqi1y foreign key (regulatory_category_id) references regulatory_category
alter table medicine add constraint FKibt3fpir0dricumntewpooutf foreign key (therapeutical_class_id) references therapeutical_class
alter table medicine_active_princible add constraint FK5sbqmoavqq0c7gpp5yjr2gryc foreign key (active_princible_id) references active_princible
alter table medicine_active_princible add constraint FK7o7dp781yf8s2xgs62kxgoi8n foreign key (medicine_id) references medicine
alter table medicine_administration_form add constraint FKmjewm2s0xcsqfgclj1vw11wtm foreign key (administration_form_id) references administration_form
alter table medicine_administration_form add constraint FKi4xc77nwkfefhk7536x2a60sn foreign key (medicine_id) references medicine
alter table medicine_pharmaceutical_form add constraint FKdug9brq86plq2h51cmcyd1lmr foreign key (pharmaceutical_form_id) references pharmaceutical_form
alter table medicine_pharmaceutical_form add constraint FK43e8j4ictch1xkl28normxvd9 foreign key (medicine_id) references medicine
alter table prescription add constraint FK1ppr8greedyrey8nchpr0v4dn foreign key (doctor_id) references doctor
alter table prescription add constraint FKqrlh184tfvdi95erwl65p4xj3 foreign key (patient_id) references patient
alter table prescription_prescription_items add constraint FK338w6djcvwh5bx8h0h54vq5pb foreign key (prescription_items_id) references prescription_item
alter table prescription_prescription_items add constraint FK72i8ishdx91g3uu69kywey3oc foreign key (prescription_id) references prescription
alter table prescription_item add constraint FK8n60qtfk33xobnq41pbuqgdcm foreign key (medicine_id) references medicine
alter table prescription_item add constraint FKeykn9e2g6nbmvwhqbrdm3jb2p foreign key (prescription_id) references prescription