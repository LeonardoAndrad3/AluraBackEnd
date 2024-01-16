package br.com.finderMusic.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface IConverter {

    ObjectMapper mapper = new ObjectMapper();
    public <T> T converter(String json, Class<T> tClass);

}
