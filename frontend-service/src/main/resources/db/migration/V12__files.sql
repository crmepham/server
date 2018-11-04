insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'applications/files', '/applications/files', 'Files', true, now(), 'system', now(), 'system', 4 from menu where external_reference = 'applications';

drop table if exists file;
create table file (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(100) not null,
	title varchar(200) not null,
	description varchar(500),
	type varchar(25) not null,
	filename varchar(250),
	path_suffix varchar(250),
	absolute_path varchar(500),
	extension varchar(10),
	enabled bit default false,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id),
	constraint unique_external_reference unique (external_reference)
) engine=InnoDB default charset=utf8;

CREATE INDEX file_external_reference ON `file` (external_reference);
CREATE INDEX file_type ON `file` (type);
CREATE INDEX file_extension ON `file` (extension);

drop table if exists file_property;
create table file_property (
	id bigint(10) unsigned not null auto_increment,
	name varchar(50) not null,
	title varchar(50) not null,
	value varchar(1000) not null,
	file_id bigint(10) unsigned not null,
	enabled bit default false,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX file_property_name ON `file_property` (name);

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('file_types', 'Image', 'The file types.', now(), 'system', now(), 'system');

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('file_storage_paths', 'Image=/home/crm/images', 'The absolute paths for file storage of different types of data.', now(), 'system', now(), 'system');