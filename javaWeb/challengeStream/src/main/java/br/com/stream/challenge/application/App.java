package br.com.stream.challenge.application;

import br.com.stream.challenge.model.ModelFipe;
import br.com.stream.challenge.model.ResponseFipe;
import br.com.stream.challenge.model.VeiculoFipe;
import br.com.stream.challenge.utils.DataManager;
import br.com.stream.challenge.utils.IScanner;
import br.com.stream.challenge.utils.RequestManager;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class App implements IScanner {
    private DataManager dataM = new DataManager();
    private RequestManager requestM = new RequestManager();
    private String json;

    public void run(){
        System.out.println("""
                **** OPÇÔES ****
                Carros
                Motos
                Caminhões
                """);
        String strRequest = scIn.nextLine();

        json = requestM.request(strRequest);

        var mark = dataM.converter(json, new TypeReference<List<ResponseFipe>>(){});
        mark.forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.tag()));

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        json = requestM.request(strRequest);

        var model = dataM.converter(json, ModelFipe.class);
        model.list().forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.tag()));

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        json = requestM.request(strRequest);

        var year = dataM.converter(json, new TypeReference<List<ResponseFipe>>(){});
        year.forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.tag()));

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        json = requestM.request(strRequest);

        var vehicle = dataM.converter(json, VeiculoFipe.class);
        System.out.println(vehicle);


    }

}
