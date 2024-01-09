package br.com.stream.challenge.utils;

import br.com.stream.challenge.model.ResponseFipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Collections;
import java.util.List;

public class DataManager implements IConverter {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converter(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json.toLowerCase(), tClass);
        } catch (JsonProcessingException | NoSuchMethodError e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> converterList(String json, Class<T> type) {

        var list = mapper.getTypeFactory().constructCollectionType(List.class, type);

        try {
            return mapper.readValue(json.toLowerCase(), list);
        } catch (JsonProcessingException | NoSuchMethodError e) {
            throw new RuntimeException(e);
        }
    }

}
