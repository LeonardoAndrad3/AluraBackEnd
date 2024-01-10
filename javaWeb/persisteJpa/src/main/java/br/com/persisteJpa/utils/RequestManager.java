package br.com.persisteJpa.utils;

import br.com.persisteJpa.application.App;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestManager {

    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;

    public String request(String uri){

        request = HttpRequest
                .newBuilder()
                .uri(URI.create(App.ADDRESS + uri))
                .build();

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

        return "";
    }
}
