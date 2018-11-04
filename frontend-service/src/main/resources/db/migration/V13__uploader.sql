insert into menu (parent_id, external_reference, uri, title, visible, created, created_user, last_updated, last_updated_user, item_order)
select id, 'applications/uploader', '/applications/uploader', 'Uploader', true, now(), 'system', now(), 'system', 5 from menu where external_reference = 'applications';

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('base_file_path', '', 'The base file path, under which all other file paths derive.', now(), 'system', now(), 'system');

update property set value = 'Image=images,Document=documents' where external_reference = 'file_storage_paths';