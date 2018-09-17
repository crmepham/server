create table reminder (
	id bigint(10) unsigned not null auto_increment,
	instruction varchar(500) not null,
	context varchar(5000) not null,
	day int(9) not null,
	month int(9) not null,
	created datetime not null,
	created_user varchar(50) not null,
	last_updated datetime,
	last_updated_user varchar(50),
	deleted bit default false,
	deleted_user varchar(50),
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE INDEX reminder_day ON `reminder` (day);
CREATE INDEX reminder_month ON `reminder` (month);