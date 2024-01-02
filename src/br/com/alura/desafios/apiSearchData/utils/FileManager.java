package br.com.alura.desafios.apiSearchData.utils;

import br.com.alura.desafios.apiSearchData.entities.Cep;
import br.com.alura.desafios.apiSearchData.records.ViaCep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager{

    FileWriter writer;
    FileReader reader;

    Gson gson;

    public FileManager(String path) throws IOException {
            File file =  new File(path);
            this.writer = new FileWriter(file);
            this.reader = new FileReader(file);
            this.gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .setPrettyPrinting()
                    .create();
    }

    void close() throws IOException {
        writer.close();
        reader.close();
    }

    void salveJson(String json) throws IOException {
        ViaCep viaCep = gson.fromJson(json, ViaCep.class);
        Cep cep = new Cep(viaCep);

        writer.write(gson.toJson(cep));
    }
}
