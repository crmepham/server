package com.server.dataservice.service.job.handler;

import static com.smattme.MysqlExportService.DB_PASSWORD;
import static com.smattme.MysqlExportService.DB_USERNAME;
import static com.smattme.MysqlExportService.JDBC_CONNECTION_STRING;
import static com.smattme.MysqlExportService.PRESERVE_GENERATED_ZIP;
import static com.smattme.MysqlExportService.TEMP_DIR;
import static java.lang.String.format;
import static java.util.Collections.singleton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;
import com.server.common.model.Action;
import com.server.common.model.DatabaseConfiguration;
import com.server.common.model.FileProperty;
import com.server.common.service.BaseService;
import com.server.common.service.FileService;
import com.server.common.service.PropertyService;
import com.server.dataservice.interfaces.JobHandler;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.DatabaseBackupRepository;
import com.server.dataservice.repository.FilePropertyRepository;
import com.server.dataservice.repository.FileRepository;
import com.smattme.MysqlExportService;
import lombok.val;
import lombok.var;

/**
 * Performs a backup of configured databases.
 *
 * @author Chris Mepham
 *
 */
@Component
public class DatabaseBackupHandler extends BaseService implements JobHandler {

    public static final String DB_BACKUP_TEMP = "db_backup_temp";
    public static final String PRESERVE_FILE = "true";
    public static final String FILE_STORAGE_PATH = "Document";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String DATABASE_CONFIGURATION_ID = "database_configuration_id";
    public static final String DATABASE_BACKUP_THRESHOLD = "database_backup_threshold";

    @Autowired
    private DatabaseBackupRepository databaseBackupRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FilePropertyRepository filePropertyRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private FileService fileService;

    @Override
    public void execute(String className) {
        val actions = actionRepository.findByClassNameAndStateAndDeletedFalseOrderByLastUpdatedDesc(className, Action.STATE_STARTED);
        val action = actions.iterator().next();

        val configurations = databaseBackupRepository.findByDeletedFalseAndEnabledTrue();
        if (configurations.isEmpty()) {
            complete("No database configurations are enabled for backup.", action);
            return;
        }

        val properties = new Properties();
        val failed = new ArrayList<String>();
        properties.setProperty(TEMP_DIR, new File(DB_BACKUP_TEMP).getPath());
        properties.setProperty(PRESERVE_GENERATED_ZIP, PRESERVE_FILE);
        val threshold = propertyService.getByExternalReference(DATABASE_BACKUP_THRESHOLD).getValue();
        for (val c : configurations) {
            properties.setProperty(DB_USERNAME, c.getUsername());
            properties.setProperty(DB_PASSWORD, c.getPassword());
            properties.setProperty(JDBC_CONNECTION_STRING, format("jdbc:mysql://%s:%s/%s", c.getHost(), c.getPort(), c.getName()));
            val mysqlExportService = new MysqlExportService(properties);

            deleteOldBackups(c, threshold);

            try {
                mysqlExportService.export();
                val generatedSql = mysqlExportService.getGeneratedSql();
                mysqlExportService.clearTempFiles(false);

                val fileMeta = new com.server.common.model.File();
                val filename = File.separator + format("%s.sql", getDateFormat());
                val pathSuffix = format("db_backup/%s/%s", c.getHost(), c.getName());
                val reference = pathSuffix + filename;

                val existing = fileRepository.findByExternalReference(reference);
                if (existing != null) {
                    continue;
                }

                fileMeta.setType(FILE_STORAGE_PATH);
                fileMeta.setPathSuffix(pathSuffix);
                fileMeta.setExternalReference(reference);
                fileMeta.setTitle(reference);
                fileMeta.setShortReference(getShortReference());

                val file = new File(reference);
                Files.createParentDirs(file);
                Files.write(generatedSql.getBytes(), file);

                fileService.store(file, fileMeta);

                val saved = fileRepository.findByExternalReference(reference);

                val property = new FileProperty();
                property.setFileId(saved.getId());
                property.setName(DATABASE_CONFIGURATION_ID);
                property.setValue(c.getId().toString());
                property.setTitle(DATABASE_CONFIGURATION_ID);
                saved.setProperties(singleton(property));
                fileService.update(saved);

                file.setReadable(true);
            } catch (Exception e) {
                failed.add(format("Failed to export database '%s' from '%s':%n%s",
                        c.getName(), c.getHost(), !e.getMessage().equalsIgnoreCase("500 null") ? e.getMessage() : "Duplicate reference."));
                continue;
            }
        }

        databaseBackupRepository.saveAll(configurations);

        var message = format("Successfully completed backup of %s databases.", configurations.size());
        if (!failed.isEmpty()) {
            message = format("%s of the %s configured database backups failed.%n%s", failed.size(), configurations.size(), failed.toString());
        }

        complete(message, action);
    }

    private void deleteOldBackups(DatabaseConfiguration c, String threshold) {
        val files = fileRepository.findByPropertyValue(DATABASE_CONFIGURATION_ID, c.getId().toString(), threshold);
        if (files.isEmpty()) {
            return;
        }

        for (val f : files) {
            new java.io.File(f.getAbsolutePath()).delete();
            filePropertyRepository.deleteAll(f.getProperties());
            fileRepository.delete(f);
        }
    }

    private String getDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN).format(new Date());
    }

    private void complete(String message, Action action) {
        action.setState(Action.STATE_COMPLETED);
        action.setResultMessage(message);
        action.setLastUpdated(new Date());
        actionRepository.save(action);
    }
}
