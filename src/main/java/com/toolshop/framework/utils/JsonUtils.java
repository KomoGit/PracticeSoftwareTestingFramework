package com.toolshop.framework.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolshop.framework.exceptions.FailedToFormatException;

/**
 * @author Elnur Abaslı
 */
public class JsonUtils {
    private final ObjectMapper mapper;

    public JsonUtils(){
        this.mapper = new ObjectMapper(new JsonFactory());
    }

    public <T> T convertToJson(String data, Class<T> clazz) {
        try {
            return mapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new FailedToFormatException(String.format("Failed to map JSON secret [%s] to class [%s].",
                    data, clazz.getSimpleName()), e);
        }
    }
}
