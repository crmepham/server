set @parentId = (select id from menu where external_reference = 'reporting');

update menu
set parent_id = @parentId,
external_reference = 'reporting/errors',
uri = '/reporting/errors'
where external_reference = 'configuration/errors';