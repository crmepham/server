package com.server.common.utils;

import com.server.common.enums.FileType;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

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

    public static final String deriveFileType(final String filename) {
        final String sub = filename.substring(0, filename.lastIndexOf("?"));
        final String extension = FilenameUtils.getExtension(sub);
        FileType type = EXTENTION_TYPES.get(extension);
        if (type == null) type = FileType.Document;
        return type.toString();
    }
}
