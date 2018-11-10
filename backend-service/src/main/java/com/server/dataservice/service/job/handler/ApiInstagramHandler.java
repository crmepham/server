package com.server.dataservice.service.job.handler;

import com.server.common.model.Action;
import com.server.common.model.File;
import com.server.common.model.FileProperty;
import com.server.common.repository.FileRepository;
import com.server.common.service.FileService;
import com.server.dataservice.interfaces.JobHandler;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.PropertyRepository;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.server.common.model.Action.STATE_COMPLETED;
import static com.server.common.model.Action.STATE_FAILED;
import static com.server.common.utils.FileUtils.deriveFileType;
import static java.io.File.separator;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.StringUtils.hasText;

/**
 * Fetches newly posted Instagram content and stores the content
 * on the local file system.
 *
 * @author Chris Mepham
 *
 */
@Component
public class ApiInstagramHandler implements JobHandler {

    private final Logger log = Logger.getLogger(ApiInstagramHandler.class.getSimpleName());

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RestTemplate template;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void execute(String className) {

        final List<Action> actions = actionRepository.findByClassNameAndStateAndDeletedFalseOrderByLastUpdatedDesc(className, Action.STATE_STARTED);
        final Action action = actions.iterator().next();
        final Map<String, Object> context = createWithProperties();

        final String userIdsString = (String) context.get("user_ids");
        final String[] userIds = userIdsString.split(",");
        if (userIds.length == 0) {
            action.setResultMessage("No user IDs found.");
            action.setState(STATE_COMPLETED);
            action.setLastUpdated(new Date());
            return;
        }

        int total = 0;
        for (int i = 0, j = userIds.length; i < j; i++) {

            final String userId = userIds[i];
            final String uri = buildUri(context, userId);
            try {
                final ResponseEntity<Map<String, Object>> res = template.exchange(uri, GET, null, new ParameterizedTypeReference<Map<String, Object>>(){});
                final Map<String, Object> body = res.getBody();

                final List<Map<String, Object>> data = (List<Map<String, Object>>) body.get("data");
                if (data.isEmpty()) {
                    action.setResultMessage("No data returned from Instagram");
                    action.setState(STATE_FAILED);
                    action.setLastUpdated(new Date());
                    return;
                }

                for (final Map<String, Object> item : data) {
                    final String type = (String) item.get("type");

                    if ("carousel".equalsIgnoreCase(type)) {

                        total += parseAndPersistCarouselFiles(item, userId);

                    } else {

                        total += parseAndPersistSingleFile(item, userId);
                    }
                }

            } catch (Exception e) {

                action.setResultMessage("Failed when communicating with Instagram.\n" + e.getMessage());
                action.setState(STATE_FAILED);
                action.setLastUpdated(new Date());
                return;
            }

        }

        action.setState(STATE_COMPLETED);
        action.setResultMessage(format("Successfully imported %s new items from Instagram.", total));
        action.setLastUpdated(new Date());
        actionRepository.save(action);

    }

    private int parseAndPersistCarouselFiles(@NonNull final Map<String, Object> item, @NonNull final String userId) throws Exception {

        final List<Map<String, Object>> carouselMedia = (List<Map<String, Object>>) item.get("carousel_media");
        int count = 0;

        for (int i = 0, j = carouselMedia.size(); i < j; i++) {

            final String id = item.get("id") + "-" + (i + 1);

            File existing = fileRepository.getByExternalReference(id);
            if (existing != null) {
                log.fine("Skipping file with external reference " + id);
                continue;
            }

            final Map<String, Object> media = carouselMedia.get(i);

            final File file = createFile(id);
            final Map<String, Object> context = populateContext(item, userId, file);
            parseAndPersist(id, media, file, context);
            count++;
        }

        return count;
    }

    private int parseAndPersistSingleFile(@NonNull final Map<String, Object> item, @NonNull final String userId)  throws Exception {

        final String id = (String) item.get("id");
        File existing = fileRepository.getByExternalReference(id);
        if (existing != null) {
            log.fine("Skipping file with external reference " + id);
            return 0;
        }

        final File file = createFile(id);
        final Map<String, Object> context = populateContext(item, userId, file);
        parseAndPersist(id, item, file, context);
        return 1;
    }

    private Map<String, Object> populateContext(@NonNull Map<String, Object> item, @NonNull String userId, File file)
    {
        Map<String, Object> caption = (Map<String, Object>) item.get("caption");

        if (caption != null) {
            file.setDescription((String) caption.get("text"));
        } else {
            file.setDescription(String.valueOf(item.get("id")));
        }

        final Map<String, Object> user = (Map<String, Object>) item.get("user");
        final long createdTime = Long.parseLong((String)item.get("created_time"));

        Map<String, Object> context = new HashMap<>();
        context.put("userId", userId);
        context.put("createdTime", createdTime);
        context.put("user", user);
        return context;
    }

    private void parseAndPersist(@NonNull final String id, @NonNull final Map<String, Object> item, @NonNull final File file, @NonNull Map<String, Object> context) throws Exception {
        final String remoteType = (String) item.get("type");
        final Map<String, Object> images = (Map<String, Object>) item.get(remoteType + "s");
        final Map<String, Object> standardResolution = (Map<String, Object>) images.get("standard_resolution");
        final String remotePath = (String) standardResolution.get("url");
        final String pathSuffix = "instagram/" + context.get("userId");

        final String type = deriveFileType(remotePath);
        file.setPathSuffix(pathSuffix);

        StringBuilder builder = new StringBuilder();
        builder.append(fileService.derivePath(type));
        builder.append(separator);

        if (hasText(pathSuffix)) {
            builder.append(fileService.sanitizePath(pathSuffix));
            builder.append(separator);
        }

        fileService.ensureDirectoryExists(builder.toString());

        builder.append(fileService.sanitizeText(remotePath.substring(remotePath.lastIndexOf('/') + 1)));

        final String absolutePath = builder.toString();

        final java.io.File newFile;
        try {
            final ResponseEntity<byte[]> res = template.exchange(remotePath, GET, null, new ParameterizedTypeReference<byte[]>(){});
            final byte[] contents = res.getBody();

            newFile = new java.io.File(absolutePath);

            FileUtils.writeByteArrayToFile(newFile, contents);

            newFile.createNewFile();

        } catch (RestClientException e) {
            throw new Exception("Failed to write contents to file at location " + absolutePath);
        } catch (IOException e) {
            throw new Exception("Failed to get remote resource from " + remotePath);
        }

        File fileMeta = new File(id, id, type);
        fileMeta.setAbsolutePath(absolutePath);

        if (hasText(pathSuffix)) {
            fileMeta.setPathSuffix(pathSuffix);
        }

        fileRepository.create(fileMeta);

        fileMeta = fileRepository.getByExternalReference(id);

        fileMeta.setProperties(createProperties(format("%.2f", fileService.getLength(newFile)), fileMeta.getId(), remotePath, item, context));
        fileRepository.create(fileMeta);
    }

    @NonNull
    private Collection<FileProperty> createProperties(@NonNull final String formattedLength,
                                                      @NonNull final long fileId,
                                                      @NonNull final String path,
                                                      @NonNull final Map<String, Object> item,
                                                      @NonNull final Map<String, Object> context) {


        final long createdTime = (long) context.get("createdTime");
        final Map<String, Object> user = (Map<String, Object>) context.get("user");
        final String remoteType = (String) item.get("type");
        final Map<String, Object> images = (Map<String, Object>) item.get(remoteType + "s");
        final Map<String, Object> standardResolution = (Map<String, Object>) images.get("standard_resolution");
        final int width = (int) standardResolution.get("width");
        final int height = (int) standardResolution.get("height");
        final FileProperty widthProperty = new FileProperty("width", "Width", valueOf(width), fileId);
        final FileProperty heightProperty = new FileProperty("height", "Height", valueOf(height), fileId);
        final FileProperty extension = new FileProperty("extension", "Extension", getExtension(path), fileId);
        final FileProperty size = new FileProperty("size", "Size", formattedLength, fileId);
        final FileProperty userId = new FileProperty("userId", "User ID", valueOf(user.get("id")), fileId);
        final FileProperty fullName = new FileProperty("fullName", "Full Name", valueOf(user.get("full_name")), fileId);
        final FileProperty filter = new FileProperty("filter", "Filter", valueOf(item.get("filter")), fileId);

        LocalDateTime date = Instant.ofEpochMilli(createdTime * 1000).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final String formatDateTime = date.format(formatter);
        final FileProperty created = new FileProperty("created", "Created", formatDateTime, fileId);

        return Arrays.asList(widthProperty, heightProperty, extension, size, userId, fullName, filter, created);
    }

    @NonNull
    private File createFile(@NonNull final String id) {
        File file = new File();
        final Date created = new Date();
        file.setCreated(created);
        file.setCreatedUser("system");
        file.setLastUpdated(created);
        file.setLastUpdatedUser("system");
        file.setExternalReference(id);
        return file;
    }

    @NonNull
    private String buildUri(@NonNull final Map<String, Object> context, @NonNull final String userId) {
        String uri = (String) context.get("base_uri");
        uri = uri.replace("#[userId]", userId).replace("#[accessToken]", (String) context.get("access_token"));
        return uri;
    }

    @NonNull
    private Map<String, Object> createWithProperties() {
        Map<String, Object> context = new HashMap<>();
        final String propertyPrefix = "api_instagram_";
        context.put("base_uri", propertyRepository.findByExternalReference(propertyPrefix + "base_uri").getValue());
        context.put("client_id", propertyRepository.findByExternalReference(propertyPrefix + "client_id").getValue());
        context.put("access_token", propertyRepository.findByExternalReference(propertyPrefix + "access_token").getValue());
        context.put("user_ids", propertyRepository.findByExternalReference(propertyPrefix + "user_ids").getValue());
        return context;
    }
}
