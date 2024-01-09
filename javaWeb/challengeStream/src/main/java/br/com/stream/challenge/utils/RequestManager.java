package br.com.stream.challenge.utils;

import br.com.stream.challenge.application.App;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class RequestManager {
    public String request(String uri){
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(App.ADDRESS + uri))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return "";
    }

    public boolean validJson(String json){
        return !json.contains("error");
    }

}
