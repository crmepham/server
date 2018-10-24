update menu set uri = '/applications/passwords' where external_reference = 'applications/passwords';
update menu set uri = '/applications/reminders' where external_reference = 'applications/reminders';

drop table if exists to_do;
create table to_do (
	id bigint(10) unsigned not null auto_increment,
	instruction varchar(500) not null,
	context varchar(5000) not null,
	pinned bit default false,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'applications/todos', '/applications/todos', 'Todos', true, now(), 'system', now(), 'system', 1 from menu where external_reference = 'applications';
