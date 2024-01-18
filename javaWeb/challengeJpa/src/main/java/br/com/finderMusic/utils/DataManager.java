package br.com.finderMusic.utils;

import br.com.finderMusic.dto.MusicDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.hibernate.engine.internal.Collections;
import org.springframework.asm.TypeReference;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager implements IConverter {

    @Override
    public <T> T converter(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> converterList(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatJson(String json, String ... data){
        try {
            JsonNode node = mapper.readTree(json);
            var artist = node.get("artist");
            ObjectNode rootNode = mapper.createObjectNode();

            for(String s : Arrays.stream(data).toList()){
                rootNode.put(s.split("/")[1], artist.at(s));
            }

            return rootNode.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean error(String json){
        System.out.println("{'err':'it content not json'}");
        return json.contains("404");
    }

}
