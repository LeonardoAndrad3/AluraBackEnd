package br.com.springProject.screenmatch.utils;

public interface IConvertData {

    <T> T getDados(String json, Class<T> tClass);

}
