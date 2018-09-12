create table secret (
	id bigint(10) unsigned not null auto_increment,
	context varchar(300) not null,
	description varchar(500),
	note varchar(2048),
	type varchar(25) not null,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX secret_type ON `secret` (type);

create table secret_property (
	id bigint(10) unsigned not null auto_increment,
	secret_id bigint(10) unsigned not null,
	name varchar(50) not null,
	value varchar(200) not null,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX secret_property_name ON `secret_property` (name);
CREATE INDEX secret_property_value ON `secret_property` (value);

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('secret_types', 'Login,Contact,Note', 'The secret types.', now(), 'system', now(), 'system');

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('secret_property_names', 'Username,Password', 'The secret types.', now(), 'system', now(), 'system');