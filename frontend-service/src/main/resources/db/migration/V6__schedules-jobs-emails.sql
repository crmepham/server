create table schedule (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(50) not null,
	title varchar(50) not null,
	description varchar(500),
	cron_expression varchar(50) not null,
	enabled bit default false,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX schedule_external_reference ON `schedule` (external_reference);

insert into schedule (external_reference,title,description,cron_expression,created,created_user)
values ('daily_early','Daily Early','Runs once per day, in the morning.','0 0 8 1/1 * ? *',now(),'system');

insert into schedule (external_reference,title,description,cron_expression,created,created_user)
values ('daily_frequent','Daily Frequent','Run every two hours of every day.','0 0 0/2 1/1 * ? *',now(),'system');

insert into schedule (external_reference,title,description,cron_expression,created,created_user)
values ('every_15_minutes','Every 15 minutes','Runs every 15 minutes of every day.','0 0/15 * 1/1 * ? *',now(),'system');

insert into schedule (external_reference,title,description,cron_expression,created,created_user)
values ('monthly','Monthly','Runs once per month, at the start of the month.','0 3 1 * * ?',now(),'system');

create table job (
	id bigint(10) unsigned not null auto_increment,
	external_reference varchar(50) not null,
	title varchar(50) not null,
	description varchar(500),
	implementation varchar(50) not null,
	enabled bit default false,
	schedule_id bigint(10) unsigned not null,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX job_external_reference ON `job` (external_reference);

insert into job (external_reference,title,description,implementation,schedule_id,created,created_user)
select 'reminders_1_week','Reminders in 1 week','A reminder that sends an email notification about important events happening in 1 week.','RemindersOneWeek', id, now(), 'system'
from schedule where external_reference = 'daily_early';

insert into job (external_reference,title,description,implementation,schedule_id,created,created_user)
select 'instagram_api','Instagram API','Backs up the Instagram images for the configured account.','ApiInstagram', id, now(), 'system'
from schedule where external_reference = 'daily_frequent';

insert into job (external_reference,title,description,implementation,schedule_id,created,created_user)
select 'database_cleanup','Database Cleanup','Deletes old entries from the database. This is backed by property ''database.cleanup.map''.','DatabaseCleanup', id, now(), 'system'
from schedule where external_reference = 'monthly';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'configuration/schedules', '/configuration/schedules', 'Schedules', true, now(), 'system', now(), 'system', 5 from menu where external_reference = 'configuration';

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('reminders_email_recipients', 'crmepham@hotmail.co.uk,tvstoddart@hotmail.co.uk', 'Reminder email recipients.', now(), 'system', now(), 'system');

create table email (
	id bigint(10) unsigned not null auto_increment,
	to_email varchar(50) not null,
	from_email varchar(50) not null,
	state varchar(50) not null,
	subject varchar(500) not null,
	body varchar(5000) not null,
	sent bit default false,
	failure_reason varchar(2000),
	retry_count int(1) default 0,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX email_to_email ON `email` (to_email);
CREATE INDEX email_from_email ON `email` (from_email);
CREATE INDEX email_subject ON `email` (subject);
CREATE INDEX email_sent ON `email` (sent);

create table action (
	id bigint(10) unsigned not null auto_increment,
	result_message varchar(2000),
	state varchar(50) not null,
	class_name varchar(100),
	object_reference varchar(50),
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX action_state ON `action` (state);
CREATE INDEX action_class_name ON `action` (class_name);

