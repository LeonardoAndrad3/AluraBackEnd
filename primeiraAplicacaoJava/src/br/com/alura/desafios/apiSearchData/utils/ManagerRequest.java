package br.com.alura.desafios.apiSearchData.utils;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;

public class ManagerRequest implements Scan {

    FinderCep finder;
    FileManager fileManager;
    String search = "";

    public ManagerRequest() {
        finder = new FinderCep();
    }

    public void run(){
        try {

            System.out.print("Write the cep you want(exit to exit): ");
            search = sc.nextLine();

            search = search.replaceAll("[\\D]", "");

            if(finder.search(search)) {
                fileManager = new FileManager(String.format("ceps/%s.json", search));
                fileManager.salveJson(finder.getResponse().body());
                System.out.println("Success search cep.");
            }

            fileManager.close();
            sc.close();

        } catch (IOException | IllegalStateException | JsonSyntaxException e) {
            System.out.println("Error search cep.");
        } finally {
            System.out.println("Close application");
        }

    }

}
