package br.com.persisteJpa.utils;

import java.util.List;

public interface IConverter {

    public <T> T converter(String json, Class<T> tClass);

    public <T> List<T> converterList(String json, Class<T> tClass);



}
