package br.com.stream.challenge.utils;

import com.fasterxml.jackson.core.type.TypeReference;

public interface IConverter {

    <T> T converter(String json, Class<T> tClass);

    <T> T converter(String json, TypeReference<T> type);
}

