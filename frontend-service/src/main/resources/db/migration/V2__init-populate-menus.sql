/* Top level menus */
insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('home', '/home', 'Home', true, now(), 'system', now(), 'system', 1);

insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('passwords', '/passwords', 'Passwords', true, now(), 'system', now(), 'system', 2);

insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('reminders', '/reminders', 'Reminders', true, now(), 'system', now(), 'system', 3);

insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('configuration', '/configuration', 'Configuration', true, now(), 'system', now(), 'system', 4);

insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('logout', '/logout', 'Logout', true, now(), 'system', now(), 'system', 5);

/* Second level configuration menus */
insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'configuration/properties', '/configuration/properties', 'Properties', true, now(), 'system', now(), 'system', 1 from menu where external_reference = 'configuration';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'configuration/users', '/configuration/users', 'Users', true, now(), 'system', now(), 'system', 3 from menu where external_reference = 'configuration';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'configuration/dashboards', '/configuration/dashboards', 'Dashboards', true, now(), 'system', now(), 'system', 4 from menu where external_reference = 'configuration';

