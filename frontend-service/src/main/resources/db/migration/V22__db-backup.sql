create table database_configuration (
	id bigint(10) unsigned not null auto_increment,
	type varchar(50) not null,
	host varchar(500) not null,
	name varchar(50) not null,
	username varchar(100) not null,
	password varchar(250) not null,
	port varchar(6) not null,
	delete_existing_data tinyint(1) default false,
	drop_tables tinyint(1) default false,
	add_if_not_exists tinyint(1) default false,
	notes varchar(3000),
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted tinyint(1) default false,
	enabled tinyint(1) default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX dc_t ON `database_configuration` (type);
CREATE INDEX dc_h ON `database_configuration` (host);
CREATE INDEX dc_n ON `database_configuration` (name);

insert into job (external_reference,title,description,implementation,schedule_id,created,created_user)
select 'database_backup','Database Backup','Will backup configured databases.','DatabaseBackup', id, now(), 'system'
from schedule where external_reference = 'daily_early';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'configuration/database', '/configuration/database', 'Databases', true, now(), 'system', now(), 'system', 6 from menu where external_reference = 'configuration';

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('database_backup_threshold', '90', 'The threshold in days, after which database backup files will be deleted.', now(), 'system', now(), 'system');