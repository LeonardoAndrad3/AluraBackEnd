package br.com.alura.apiSearchData;

import br.com.alura.aula.resource.Title;
import br.com.alura.aula.resource.TitleOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

public class Application {

    Scanner sc = new Scanner(System.in);
    String search = "";
    List<Title> titles = new ArrayList<>();


    public static void main(String[] args) {
        new Application().connect();
    }

    public void connect()  {

        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(!search.equalsIgnoreCase("exit")){

            System.out.print("Write the film for search:");
            search = sc.nextLine();

            if(search.equalsIgnoreCase("exit")) break;

            String strSearch = String.format("http://www.omdbapi.com/?t=%s&apikey=d447ad04", search);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(strSearch))
                    .build();

            try {
                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

                String respStr = response.body();

            //            Title title = gson.fromJson(respStr, Title.class);

                TitleOmdb titleOmdb = gson.fromJson(respStr, TitleOmdb.class);

                Title title = new Title(titleOmdb);

                titles.add(title);

//                FileWriter writer = new FileWriter("films.txt");
//                writer.write(title.toString());
//                writer.close();

                FileWriter write = new FileWriter("titles.json");
                write.write(gson.toJson(titles));
                write.close();

            } catch (IOException | InterruptedException | NumberFormatException | IllegalFormatException e) {
                System.out.println(e.getMessage() + e.getCause());
            } finally {

                System.out.println(titles);
            }
        }

        System.out.println("Finish system");
    }
}

