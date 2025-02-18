package org.example.msorder.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.example.msorder.exception.CustomFeignException;
import org.example.msorder.model.enums.ErrorMessage;

import static org.example.msorder.util.MapperUtil.MAPPER_UTIL;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        var statusCode = response.status();
        var errorMessage = ErrorMessage.CLIENT_ERROR.getMessage();

        JsonNode jsonNode;

        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception exception) {
            throw new CustomFeignException(errorMessage, statusCode);
        }
        if (jsonNode.has(JsonNodeFieldName.MESSAGE.getMessage()))
            errorMessage = jsonNode.get(JsonNodeFieldName.MESSAGE.getMessage()).asText();
        return new CustomFeignException(errorMessage, statusCode);

    }
}
