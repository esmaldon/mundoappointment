alter table patients
	add column admission_date date not null,
	add column status varchar(20) not null default 'ACTIVO',
	add column referred_by varchar(100),
	add column discharge_reason varchar(255);
