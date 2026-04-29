package com.toolshop.framework.utils;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {

    public static Path getFile(String fileName) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(String.format("files/%s", fileName)).toURI());
    }

}
