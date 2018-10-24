insert into menu (external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
values ('applications', '/applications', 'Applications', true, now(), 'system', now(), 'system', 2);

set @menuId = (select id from menu where external_reference = 'applications');

update menu set parent_id = @menuId,
external_reference = 'applications/passwords',
uri = 'applications/passwords'
where external_reference = 'passwords';

update menu set parent_id = @menuId,
external_reference = 'applications/reminders',
uri = 'applications/reminders'
where external_reference = 'reminders';