insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('api_instagram_access_token', '240809256.a958a0d.5306cb0c34114fafb5b63494a6137fe9', 'The Instagram API access token.', now(), 'system', now(), 'system');

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('api_instagram_base_uri', 'https://api.instagram.com/v1/users/#[userId]/media/recent/?access_token=#[accessToken]', 'The Instagram API base URI.', now(), 'system', now(), 'system');

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('api_instagram_client_id', 'a958a0d17af94fd68b2c8d30c46b4f70', 'The Instagram API client ID.', now(), 'system', now(), 'system');

insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('api_instagram_user_ids', '240809256', 'A comma-separated list of Instgram user IDs.', now(), 'system', now(), 'system');

update property set value = 'Image=images,Document=documents,Video=videos' where external_reference = 'file_storage_paths';