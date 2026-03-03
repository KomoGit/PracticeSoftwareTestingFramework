package com.toolshop.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.toolshop.framework.constants.ConfigConstants;
import com.toolshop.framework.models.FrameworkConfig;
import java.io.File;
import java.io.IOException;

public class ConfigReader {
    public static FrameworkConfig readConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(ConfigConstants.RESOURCE_PATH), FrameworkConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read config.yaml", e);
        }
    }
}