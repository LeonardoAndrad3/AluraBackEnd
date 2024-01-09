package br.com.stream.challenge.utils;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverter {

    <T> T converter(String json, Class<T> tClass);

    <T> List<T> converterList(String json, Class<T> type);
}

