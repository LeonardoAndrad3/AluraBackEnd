package br.com.springProject.screenmatch.service;

public interface IConvertData {

    <T> T getDados(String json, Class<T> tClass);

}
