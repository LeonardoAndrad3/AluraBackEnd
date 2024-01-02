package br.com.alura.desafios.apiSearchData.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.IllegalFormatException;

public class FinderCep {

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;

    public FinderCep() {
        this.client = HttpClient.newHttpClient();
    }

    public boolean search(String cep){
        try{
            String searchCep = String.format("https://viacep.com.br/ws/%s/json/", cep);
            request = HttpRequest.newBuilder()
                    .uri(URI.create(searchCep))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return true;
        } catch(InterruptedException | IOException | IllegalFormatException e){
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public HttpResponse<String> getResponse() {
        return response;
    }

}
