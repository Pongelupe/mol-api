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
drop table prescription_prescripton_items if exists
drop table prescription_item if exists
drop table regulatory_category if exists
drop table therapeutical_class if exists
create table active_princible (id varchar(36) not null, description varchar(80), primary key (id))
create table administration_form (id varchar(36) not null, description varchar(80), primary key (id))
create table atc (id varchar(36) not null, description varchar(80), primary key (id))
create table contraindication (id varchar(36) not null, description varchar(200), patient_id varchar(36) not null, primary key (id))
create table contraindication_medicines (contraindication_id varchar(36) not null, medicines_id varchar(36) not null, primary key (contraindication_id, medicines_id))
create table doctor (id varchar(36) not null, active boolean, birth_date date not null, confirmed boolean, cpf varchar(14) not null, created_at timestamp, email varchar(30) not null, gender varchar(8) not null, name varchar(50) not null, password varchar(60) not null, phone varchar(50), reset_password boolean, updated_at timestamp, crm varchar(30), digital_signature blob not null, field varchar(50), rg varchar(20), state varchar(2), primary key (id))
create table medicine (id varchar(36) not null, comercial_name varchar(255), company_name varchar(80), expiration integer, fractionable boolean, intended_for integer, monodrug boolean, prescription_restriction varchar(255), presentation_diff_complement varchar(255), preservation varchar(255), public_report varchar(255), record_number bigint, record_publication date, record_status varchar(255), tarja varchar(255), atc_id varchar(36), medicine_of_reference_id varchar(36), regulatory_category_id varchar(36), therapeutical_class_id varchar(36), primary key (id))
create table medicine_active_princible (medicine_id varchar(36) not null, active_princible_id varchar(36) not null, primary key (medicine_id, active_princible_id))
create table medicine_administration_form (medicine_id varchar(36) not null, administration_form_id varchar(36) not null, primary key (medicine_id, administration_form_id))
create table medicine_pharmaceutical_form (medicine_id varchar(36) not null, pharmaceutical_form_id varchar(36) not null, primary key (medicine_id, pharmaceutical_form_id))
create table patient (id varchar(36) not null, active boolean, birth_date date not null, confirmed boolean, cpf varchar(14) not null, created_at timestamp, email varchar(30) not null, gender varchar(8) not null, name varchar(50) not null, password varchar(60) not null, phone varchar(50), reset_password boolean, updated_at timestamp, primary key (id))
create table pharmaceutical_form (id varchar(36) not null, description varchar(80), primary key (id))
create table prescription (id varchar(36) not null, created_at timestamp, observation varchar(200), shelf_life date, doctor_id varchar(36) not null, primary key (id))
create table prescription_prescripton_items (prescription_id varchar(36) not null, prescripton_items_id varchar(36) not null, primary key (prescription_id, prescripton_items_id))
create table prescription_item (id varchar(36) not null, quantity double, medicine_id varchar(36) not null, prescription_id varchar(36) not null, primary key (id))
create table regulatory_category (id varchar(36) not null, description varchar(80), primary key (id))
create table therapeutical_class (id varchar(36) not null, description varchar(80), primary key (id))
alter table doctor add constraint UK_DOCTOR_CPF unique (cpf) 
alter table doctor add constraint UK_DOCTOR_EMAIL unique (email)
alter table doctor add constraint UK_DOCTOR_CRM unique (crm)
alter table doctor add constraint UK_DOCTOR_RG unique (rg)
alter table medicine_administration_form add constraint UK_MEDICINE_ADMINISTRATION_FORM_ID unique (administration_form_id)
alter table patient add constraint UK_PACIENT_CPF unique (cpf)
alter table patient add constraint UK_PACIENT_EMAIL unique (email)
alter table prescription_prescripton_items add constraint UK_PRESCRIPTION_PRESCRIPTON_ITEMS_ID unique (prescripton_items_id)
alter table contraindication add constraint FK_CONTRAINDICATION_PACIENT foreign key (patient_id) references patient
alter table contraindication_medicines add constraint FK_CONTRAINDICATION_MEDICINES_MEDICINE foreign key (medicines_id) references medicine
alter table contraindication_medicines add constraint FK_CONTRAINDICATION_MEDICINES_CONTRAINDICATION foreign key (contraindication_id) references contraindication
alter table medicine add constraint FK_MEDICINE_ATC foreign key (atc_id) references atc
alter table medicine add constraint FK_MEDICINE_MEDICINE foreign key (medicine_of_reference_id) references medicine
alter table medicine add constraint FK_MEDICINE_REGULATORY_CATEGORY foreign key (regulatory_category_id) references regulatory_category
alter table medicine add constraint FK_MEDICINE_THERAPEUTICAL_CLASS foreign key (therapeutical_class_id) references therapeutical_class
alter table medicine_active_princible add constraint FK_MEDICINE_ACTIVE_PRINCIBLE_MEDICINE_ACTIVE_PRINCIBLE foreign key (active_princible_id) references active_princible
alter table medicine_active_princible add constraint FK_MEDICINE_ACTIVE_PRINCIBLE_MEDICINE foreign key (medicine_id) references medicine
alter table medicine_administration_form add constraint FK_MEDICINE_ADMINISTRATION_FORM_MEDICINE_ADMINISTRATION_FORM foreign key (administration_form_id) references administration_form
alter table medicine_administration_form add constraint FK_MEDICINE_ADMINISTRATION_FORM_MEDICINE foreign key (medicine_id) references medicine
alter table medicine_pharmaceutical_form add constraint FK_MEDICINE_PHARMACEUTICAL_FORM_PHARMACEUTICAL_FORM foreign key (pharmaceutical_form_id) references pharmaceutical_form
alter table medicine_pharmaceutical_form add constraint FK_MEDICINE_PHARMACEUTICAL_FORM_MEDICINE foreign key (medicine_id) references medicine
alter table prescription add constraint FK_PRESCRIPTION_DOCTOR foreign key (doctor_id) references doctor
alter table prescription_prescripton_items add constraint FK_PRESCRIPTION_PRESCRIPTON_ITEMS_PRESCRIPTION_ITEM foreign key (prescripton_items_id) references prescription_item
alter table prescription_prescripton_items add constraint FK_PRESCRIPTION_PRESCRIPTON_ITEMS_PRESCRIPTION foreign key (prescription_id) references prescription
alter table prescription_item add constraint FK_PRESCRIPTION_ITEM_MEDICINE foreign key (medicine_id) references medicine
alter table prescription_item add constraint FK_PRESCRIPTION_ITEM_PRESCRIPTION foreign key (prescription_id) references prescription
