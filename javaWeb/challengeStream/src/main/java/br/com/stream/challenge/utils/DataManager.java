package br.com.stream.challenge.utils;

import br.com.stream.challenge.model.ResponseFipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

public class DataManager implements IConverter {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converter(String json, Class<T> tClass) {
        try {
            System.out.println(json.toLowerCase());

            return mapper.readValue(json.toLowerCase(), tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T converter(String json, TypeReference<T> type) {
        try {
            System.out.println(json.toLowerCase());
            return mapper.readValue(json.toLowerCase(), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
