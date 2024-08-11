\c transaction_db;
create table master.users(
	user_id integer primary key,
	first_name varchar(255) not null,
	last_name varchar(255),
	is_active boolean,
	ins_time timestamp not null,
	upd_time timestamp not null
);

create table master.merchant(
	merchant_id integer primary key,
	first_name varchar(255) not null,
	last_name varchar(255),
	is_active boolean,
	ins_time timestamp not null,
	upd_time timestamp not null
);

create table master.card_type(
	card_type_id smallint primary key,
	name varchar(255) not null,
	default_transaction_limit numeric(10,2) not null,
	default_daily_transaction_limit numeric(10,2) not null,
	ins_time timestamp not null,
	upd_time timestamp not null
);

create table master.cards(
	card_number varchar(16) primary key,
	card_type_id smallint references master.card_type(card_type_id),
	card_limit numeric(10,2) not null,
	card_balance numeric(10,2) not null,
	parent_card uuid,
	user_id integer references master.users(user_id),
	is_active boolean,
	ins_time timestamp not null,
	upd_time timestamp not null
);

create unique index cards_numer_idx on master.cards(card_number);


create table master.transaction_log(
	transaction_id uuid primary key,
	card_number varchar(16) references master.cards(card_number),
	merchant_id integer references master.merchant(merchant_id),
	transaction_type varchar(20),
	amount numeric(10,2),
	is_executed boolean,
	authorization_status varchar(50),
	ins_time timestamp,
	upd_time timestamp
	
);

create index transaction_log_card_idx on master.transaction_log(card_number);
create index transaction_log_merchant_idx on master.transaction_log(merchant_id);

