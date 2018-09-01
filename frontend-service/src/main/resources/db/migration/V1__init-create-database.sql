drop table if exists user;
create table user (
	id bigint(10) unsigned not null auto_increment,
	username varchar(50) not null,
	password varchar(150) not null,
	account_non_expired bit default true,
	account_non_locked bit default true,
	credentials_non_expired bit default true,
	enabled bit default false,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	visible bit default false,
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id),
	unique(username)
) engine=InnoDB default charset=utf8;

CREATE INDEX user_username ON `user` (username);
CREATE INDEX user_password ON `user` (password);

drop table if exists property;
create table property (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(50) not null,
	value varchar(2048),
	description varchar(500),
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	visible bit default false,
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id),
	unique(external_reference)
) engine=InnoDB default charset=utf8;

CREATE INDEX property_external_reference ON `property` (external_reference);
CREATE INDEX property_description ON `property` (description);

drop table if exists menu;
create table menu (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(50) not null,
	uri varchar(1024) not null,
	title varchar(50),
	item_order int(10) unsigned,
	parent_id bigint(10) unsigned,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	visible bit default false,
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id),
	unique(uri)
) engine=InnoDB default charset=utf8;

CREATE INDEX menu_external_reference ON `menu` (external_reference);
CREATE INDEX menu_title ON `menu` (title);

drop table if exists dashboard;
create table dashboard (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(50),
	uri varchar(100),
	content varchar(10000),
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	enabled bit default false,
	deleted bit default false,
	deleted_user varchar(50),
	completed datetime,
	completed_user varchar(50),
	primary key (id),
	unique(uri, enabled)
) engine=InnoDB default charset=utf8;

CREATE INDEX dashboard_external_reference ON `dashboard` (external_reference);
CREATE INDEX dashboard_uri ON `dashboard` (uri);

insert into user (username, password, enabled, created, created_user)
values ('crm', 'AAER8]HR6UnR4knR4YnRnN[]', true, now(), 'system');