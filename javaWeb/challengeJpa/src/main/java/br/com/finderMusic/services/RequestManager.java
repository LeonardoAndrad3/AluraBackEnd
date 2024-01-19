package br.com.finderMusic.services;

import br.com.finderMusic.application.App;
import br.com.finderMusic.enums.TypeRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class RequestManager {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;

    HttpResponse<String> response;

    public String toRequest(String address, String sendRequest){
        builderUri(address+sendRequest);
        return trySend();
    }

    public String toRequest(String sendRequest){
        builderUri(sendRequest);
        return trySend();
    }

    public static String formatString(String model, String replace, String ... values){
        return String.format(model, values).replace(" ", replace).trim();
    }

    public String trySend(){
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(!response.body().contains("404"))
                return response.body();
            else
                return "{'err':'it content not json'}";
        } catch (IOException | InterruptedException e) {
            return "{'err':'"+e.getMessage()+"'}";
        }
    }

    public void builderUri(String strRequest){
        request = HttpRequest
                .newBuilder()
                .uri(URI.create(strRequest.trim()))
                .build();
    }
}
