ALTER TABLE file ADD short_reference varchar(10);

update file set short_reference = (SUBSTRING(MD5(RAND()) FROM 1 FOR 5));