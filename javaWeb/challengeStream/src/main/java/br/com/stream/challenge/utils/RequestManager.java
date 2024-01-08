package br.com.stream.challenge.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class RequestManager {

    private final String ADDRESS = "https://parallelum.com.br/fipe/api/v1/";
    private String strSearch = "";
    public static int requesting = 0;
    public String request(String uri){
        strSearch += appendUri(uri);

        System.out.println(String.format("%s%s", ADDRESS, strSearch));


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(
                        String.format("%s%s", ADDRESS, strSearch)))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

        return "";
    }

    private String appendUri(String uri) {

        uri = uri.toLowerCase();

        if(requesting == 0){
            if(!uri.contains("c") && uri.contains("m")) uri = "motos";
            else if(uri.contains("m")) uri = "caminhoes";
            else if(uri.contains("rr")) uri = "carros";
            else throw new RuntimeException("Vehicle not find");
        }

        switch (requesting) {
            case 0 -> uri = uri + "/marcas/";
            case 1 -> uri = uri + "/modelos/";
            case 2 -> uri = uri + "/anos/";
            default -> uri = uri;
        }

        ++requesting;
        return uri;
    }

}
