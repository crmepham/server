insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('reporting', '/reporting', 'Reporting', true, now(), 'system', now(), 'system', 5);

update menu set item_order = 6 where external_reference = 'logout';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'reporting/emails', '/reporting/emails', 'Emails', true, now(), 'system', now(), 'system', 1 from menu where external_reference = 'reporting';

insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'reporting/actions', '/reporting/actions', 'Actions', true, now(), 'system', now(), 'system', 2 from menu where external_reference = 'reporting';