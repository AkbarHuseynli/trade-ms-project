package org.example.msorder.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public enum MapperUtil {
    MAPPER_UTIL;

    public <T> T map(InputStream source, Class<T> target){
        try {
               return new ObjectMapper().readValue(source, target);
        }
        catch (Exception exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
