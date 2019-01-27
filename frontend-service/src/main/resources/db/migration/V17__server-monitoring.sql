insert into schedule (external_reference,title,description,cron_expression,created,created_user)
values ('every_minute','Every minute','Runs once per minute.','0 0/1 * 1/1 * ? *',now(),'system');

insert into job (external_reference,title,description,implementation,schedule_id,created,created_user)
select 'monitor_notification','Monitor Notification','Sends an email to the specified recipients if a monitored server is unreachable.','Monitor', id, now(), 'system'
from schedule where external_reference = 'every_15_minutes';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'applications/monitor', '/applications/monitor', 'Monitor', true, now(), 'system', now(), 'system', 7 from menu where external_reference = 'applications';

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('monitor_email_recipients', 'crmepham@hotmail.co.uk', 'A comma-separated list of email recipients that will receive Monitor email notifications.', now(), 'system', now(), 'system');

CREATE TABLE `monitor` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `deleted_user` varchar(50) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `last_updated_user` varchar(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `uri` varchar(200) NOT NULL,
  `note` varchar(2000),
  `reachable` bit default false,
  `notify` bit default true,
  PRIMARY KEY (`id`),
  UNIQUE(`uri`),
  UNIQUE(`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE INDEX monitor_name ON `monitor` (name);
CREATE INDEX monitor_uri ON `monitor` (uri);