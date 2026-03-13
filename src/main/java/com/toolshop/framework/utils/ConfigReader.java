package com.toolshop.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.toolshop.framework.constants.ConfigConstants;
import com.toolshop.framework.exceptions.FailedToFormatException;
import com.toolshop.framework.models.FrameworkConfigModel;
import java.io.File;
import java.io.IOException;

public class ConfigReader {
    public static FrameworkConfigModel readConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = String.format(
                "%s%s",
                ConfigConstants.RESOURCE_PATH,
                "config.yaml"
        );
        try {
            return mapper.readValue(new File(path), FrameworkConfigModel.class);
        } catch (IOException e) {
            throw new FailedToFormatException("Failed to read config.yaml", e);
        }
    }
}