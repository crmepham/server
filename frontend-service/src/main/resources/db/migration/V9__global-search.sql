insert into property (external_reference, value, description, created, created_user, last_updated, last_updated_user)
values ('global_search', 'Passwords=password:type,context,description:type,context,description;Reminders=reminder:instruction,context:instruction,context,action_date',
'Defines the tables and columns that the search will be applied to. Format is Table display name : columns to search : columns to display.', now(), 'system', now(), 'system');
