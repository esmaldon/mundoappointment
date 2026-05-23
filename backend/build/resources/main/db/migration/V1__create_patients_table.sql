create table patients (
	id uuid primary key,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	birth_date date not null,
	guardian_name varchar(200) not null,
	phone_number varchar(30) not null,
	email varchar(255),
	created_at timestamp with time zone not null
);
