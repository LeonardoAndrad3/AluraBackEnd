package br.com.finderMusic.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface IConverter {

    ObjectMapper mapper = new ObjectMapper();
    public <T> T converter(String json, Class<T> tClass);

    public <T> List<T> converterList(String json, Class<T> tClass);

}
