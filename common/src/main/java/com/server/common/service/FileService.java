package com.server.common.service;

import static com.server.common.utils.FileUtils.BASE_FILE_PATH;
import static com.server.common.utils.FileUtils.FILE_STORAGE_PATHS;
import static com.server.common.utils.FileUtils.deriveFileType;
import static java.io.File.separator;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.server.common.model.File;
import com.server.common.model.FileProperty;
import com.server.common.model.InputResult;
import com.server.common.repository.FileRepository;

@Transactional
@Service
public class FileService extends BaseService
{
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PropertyService propertyService;

    public CompletableFuture<List<File>> getAll() {

        return fileRepository.getAll();
    }

    public CompletableFuture<List<File>> getAllImages() {

        return fileRepository.getAllImages();
    }

    public void update(File file) {
        file.setPathSuffix(sanitizePath(file.getPathSuffix()));
        fileRepository.create(file);
    }

    public void delete(long id) throws ExecutionException, InterruptedException {
        delete(getById(id).get());
        fileRepository.delete(id);
    }

    public CompletableFuture<File> getById(long id) {
        return fileRepository.getById(id);
    }

    public File getByExternalReference(final String externalReference) {
        return fileRepository.getByExternalReference(externalReference);
    }

    public File getByShortReference(final String shortReference) {
        return fileRepository.getByShortReference(shortReference);
    }

    public boolean exists(final File file) {
        return new java.io.File(file.getAbsolutePath()).exists();
    }

    public boolean store(final MultipartFile multipartFile, final File fileMeta) {

        try {
            final String path = getPath(fileMeta);
            ensureDirectoryExists(path);
            final String absolutePath = path + separator + multipartFile.getOriginalFilename();
            java.io.File file = new java.io.File(absolutePath);
            multipartFile.transferTo(file);
            fileMeta.setProperties(createProperties(format("%.2f", getLength(file)), fileMeta.getId(), Paths.get(absolutePath)));
            fileMeta.setAbsolutePath(file.getAbsolutePath());
            fileMeta.setLastUpdated(new Date());
            update(fileMeta);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public InputResult store(final MultipartFile[] files, final String pathSuffix) throws IOException {

        final int totalCount = files.length;
        int successCount = 0;

        for (int i = 0, j = files.length; i < j; i++) {

            if (store(files[i], pathSuffix)) {
                successCount++;
            }
        }

        if (successCount == totalCount) {
            return new InputResult(successCount);
        }

        String message;
        if (successCount != totalCount) {
            message = format("Failed to upload and persist %s of the %s selected files.",
                    (totalCount - successCount), totalCount);
        } else {
            message = format("Failed to upload and persist all %s selected files.", totalCount);
        }

        return new InputResult(message);
    }

    private boolean store(final MultipartFile file, final String pathSuffix) throws IOException {

        final String reference = file.getOriginalFilename();

        final File existing = fileRepository.getByExternalReference(reference);
        if (existing != null)
        {
            return false;
        }

        StringBuilder builder = new StringBuilder();
        final String type = deriveFileType(file.getOriginalFilename());
        builder.append(derivePath(type));
        builder.append(separator);

        if (hasText(pathSuffix)) {
            builder.append(sanitizePath(pathSuffix));
            builder.append(separator);
        }

        ensureDirectoryExists(builder.toString());

        builder.append(sanitizeText(file.getOriginalFilename()));

        final String absolutePath = builder.toString();

        java.io.File newFile = new java.io.File(absolutePath);

        try
        {
            file.transferTo(newFile);
        } catch (IOException e) {
            return false;
        }

        File fileMeta = new File(reference, reference, type);
        fileMeta.setAbsolutePath(absolutePath);

        if (hasText(pathSuffix)) {
            fileMeta.setPathSuffix(pathSuffix);
        }

        fileMeta.setShortReference(getShortReference());


        fileRepository.create(fileMeta);

        fileMeta = fileRepository.getByExternalReference(reference);

        fileMeta.setProperties(createProperties(format("%.2f", getLength(newFile)), fileMeta.getId(), Paths.get(absolutePath)));
        fileRepository.create(fileMeta);

        return true;
    }

    private String getShortReference()
    {
        String shortReference = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toLowerCase();
        while (getByShortReference(shortReference) != null)
        {
            shortReference = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toLowerCase();
        }
        return shortReference;
    }

    private List<FileProperty> createProperties(final String formattedLength, final long fileId, final Path path) throws IOException {
        final FileProperty extension = new FileProperty("extension", "Extension", FilenameUtils.getExtension(path.getFileName().toString()), fileId);
        final FileProperty size = new FileProperty("size", "Size", formattedLength, fileId);

        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        final FileProperty created = new FileProperty("created", "Created", attr.creationTime().toString(), fileId);
        final FileProperty lastModified = new FileProperty("last_modified", "Last modified", attr.lastModifiedTime().toString(), fileId);
        final FileProperty lastOpened = new FileProperty("last_opened", "Last opened", attr.lastAccessTime().toString(), fileId);

        return Arrays.asList(extension, size, created, lastModified, lastOpened);
    }

    public String derivePath(final String type) {
        StringBuilder builder = new StringBuilder();
        builder.append(propertyService.getByExternalReference(BASE_FILE_PATH).getValue().trim());
        builder.append(separator);

        final String filePathSuffix = getFilePaths().get(type);
        if (hasText(filePathSuffix)) {
            builder.append(filePathSuffix);
        }
        else {
            builder.append("documents");
        }

        return builder.toString();
    }

    public void delete(final File file) {

        if (hasText(file.getAbsolutePath())) {
            new java.io.File(file.getAbsolutePath()).delete();
        }

        propertyService.deleteAll(file.getId());

        file.setProperties(null);
        file.setLastUpdated(new Date());
        update(file);
    }

    public double getLength(java.io.File file) {
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        return kilobytes / 1024;
    }

    public String sanitizeText(String input) {
        input = input.trim();
        input = input.replace(" ", "-");
        if (input.lastIndexOf("?") != -1) {
            input = input.substring(0, input.lastIndexOf("?"));
        }
        return input;
    }

    public String sanitizePath(final String pathSuffix) {
        if (!hasText(pathSuffix)) {
            return null;
        }

        String sanitized = pathSuffix.toLowerCase(Locale.getDefault());

        if (sanitized.charAt(0) == '/') {
            sanitized = sanitized.substring(1);
        }

        if (sanitized.charAt(sanitized.length()-1) == '/') {
            sanitized = sanitized.substring(0, sanitized.length()-1);
        }

        return sanitized;
    }

    public void ensureDirectoryExists(final String path) {
        java.io.File directory = new java.io.File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private String getPath(File fileMeta) {

        StringBuilder builder = new StringBuilder();
        builder.append(propertyService.getByExternalReference(BASE_FILE_PATH).getValue().trim());
        builder.append(separator);
        builder.append(getFilePaths().get(fileMeta.getType()));

        if (hasText(fileMeta.getPathSuffix())) {
            builder.append(separator);
            builder.append(fileMeta.getPathSuffix());
        }

        return builder.toString();
    }

    private Map<String, String> getFilePaths()
    {
        final String value = propertyService.getByExternalReference(FILE_STORAGE_PATHS).getValue();
        return Arrays.stream(value.split(","))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(
                        a -> a[0],
                        a -> a[1]
                ));
    }
}
