package br.com.challengeApiFilms.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RequestManager {

    private HttpRequest request;
    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private String address;

    public RequestManager() {
    }

    public RequestManager(String address) {
        this.address = address;
    }

    public String requesting(String str){
        String uri = formatString(address, str);
        request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = send();
        return response.body();
    }

    public static String formatString(String model, String ... data){
        return String.format(model, data);
    }

    public HttpResponse<String> send(){
        try{
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException |InterruptedException e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
