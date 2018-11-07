package com.server.frontendservice.service;

import com.server.common.enums.FileType;
import com.server.common.model.File;
import com.server.common.model.FileProperty;
import com.server.common.model.InputResult;
import com.server.frontendservice.repository.FileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Transactional
@Service
public class FileService extends BaseService
{
    public static final String BASE_FILE_PATH = "base_file_path";
    public static final String FILE_STORAGE_PATHS = "file_storage_paths";

    public static final Map<String, FileType> EXTENTION_TYPES = new HashMap<>();

    static {
        EXTENTION_TYPES.put("png", FileType.Image);
        EXTENTION_TYPES.put("gif", FileType.Image);
        EXTENTION_TYPES.put("jpg", FileType.Image);
        EXTENTION_TYPES.put("bmp", FileType.Image);
        EXTENTION_TYPES.put("pdf", FileType.Document);
        EXTENTION_TYPES.put("xls", FileType.Document);
        EXTENTION_TYPES.put("doc", FileType.Document);
        EXTENTION_TYPES.put("docx", FileType.Document);
        EXTENTION_TYPES.put("txt", FileType.Document);
        EXTENTION_TYPES.put("rtf", FileType.Document);
        EXTENTION_TYPES.put("xml", FileType.Document);
        EXTENTION_TYPES.put("csv", FileType.Document);
        EXTENTION_TYPES.put("ods", FileType.Document);
        EXTENTION_TYPES.put("odt", FileType.Document);
        EXTENTION_TYPES.put("oxt", FileType.Document);
        EXTENTION_TYPES.put("mov", FileType.Video);
        EXTENTION_TYPES.put("avi", FileType.Video);
        EXTENTION_TYPES.put("wmv", FileType.Video);
        EXTENTION_TYPES.put("mp4", FileType.Video);
        EXTENTION_TYPES.put("flv", FileType.Video);
        EXTENTION_TYPES.put("mpg", FileType.Video);
        EXTENTION_TYPES.put("3pg", FileType.Video);
        EXTENTION_TYPES.put("asf", FileType.Video);
        EXTENTION_TYPES.put("rm", FileType.Video);
        EXTENTION_TYPES.put("swf", FileType.Video);
    }


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PropertyService propertyService;

    public CompletableFuture<List<File>> getAll() {

        return fileRepository.getAll();
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

        final String reference = "undefined-" + file.getOriginalFilename();

        final File existing = fileRepository.getByExternalReference(reference);
        if (existing != null)
        {
            return false;
        }

        StringBuilder builder = new StringBuilder();
        final String type = deriveFileType(file);
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

        fileRepository.create(fileMeta);

        fileMeta = fileRepository.getByExternalReference(reference);

        fileMeta.setProperties(createProperties(format("%.2f", getLength(newFile)), fileMeta.getId(), Paths.get(absolutePath)));
        fileRepository.create(fileMeta);

        return true;
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

    private String derivePath(final String type) {
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

    private String deriveFileType(final MultipartFile file) {
        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        FileType type = EXTENTION_TYPES.get(extension);
        if (type == null) type = FileType.Document;
        return type.toString();
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

    private double getLength(java.io.File file) {
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        return kilobytes / 1024;
    }

    private String sanitizeText(String input) {
        input = input.trim();
        input = input.replace(" ", "-");
        return input;
    }

    private String sanitizePath(final String pathSuffix) {
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

    private void ensureDirectoryExists(final String path) {
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
