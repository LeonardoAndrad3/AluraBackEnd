package br.com.finderMusic.services;

import br.com.finderMusic.application.App;
import br.com.finderMusic.enums.TypeRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestManager {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;

    HttpResponse<String> response;

    public String toRequest(String address, String sendRequest){

        request = HttpRequest
                .newBuilder()
                .uri(URI.create(address + sendRequest.trim()))
                .build();

        System.out.println(request);

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toRequest(String sendRequest){

        request = HttpRequest
                .newBuilder()
                .uri(URI.create(sendRequest.trim()))
                .build();

        System.out.println(request);

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatString(String model, String replace , String ... values){
        return String.format(model, values).replace(" ", replace).trim();
    }

}
