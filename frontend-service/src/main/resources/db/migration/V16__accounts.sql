insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'applications/accounts', '/applications/accounts', 'Accounts', true, now(), 'system', now(), 'system', 6 from menu where external_reference = 'applications';

CREATE TABLE `account_transaction` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `deleted_user` varchar(50) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `last_updated_user` varchar(50) DEFAULT NULL,
  `account_id` bigint(11) DEFAULT NULL,
  `name` varchar(500) NOT NULL,
  `type` varchar(10) NOT NULL,
  `value` double NOT NULL,
  `percent` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE INDEX idx_account_transaction_name ON `account_transaction` (name);
CREATE INDEX idx_account_transaction_type ON `account_transaction` (type);

CREATE TABLE `account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `deleted_user` varchar(50) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `last_updated_user` varchar(50) DEFAULT NULL,
  `name` varchar(500) NOT NULL,
  `note` varchar(2000),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE INDEX idx_account_name ON `account` (name);