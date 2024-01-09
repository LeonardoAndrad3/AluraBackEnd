package br.com.stream.challenge.application;

import br.com.stream.challenge.exceptions.InvalidVehicleTypeException;
import br.com.stream.challenge.exceptions.NotFindException;
import br.com.stream.challenge.model.apiFipe.ModelFipe;
import br.com.stream.challenge.model.apiFipe.ResponseFipe;
import br.com.stream.challenge.model.apiFipe.VeiculoFipe;
import br.com.stream.challenge.utils.DataManager;
import br.com.stream.challenge.utils.IScanner;
import br.com.stream.challenge.utils.RequestManager;

import java.util.Comparator;
import java.util.stream.Collectors;

public class AppFipe implements IScanner {

    public static final String ADDRESS = "https://parallelum.com.br/fipe/api/v1/";
    private DataManager dataM = new DataManager();
    private RequestManager requestM = new RequestManager();
    private String json;

    private String uri = "";
    private String strRequest="";

    public void run(){
        var str = """
                    **** OPÇÔES ****
                    Carros
                    Motos
                    Caminhões
                    """;

        System.out.println(str);
        String strRequest = scIn.nextLine();

        if(strRequest.toLowerCase().contains("car"))
            strRequest = "carros";
        else if (strRequest.toLowerCase().contains("mo"))
            strRequest = "motos";
        else
            strRequest = "caminhoes";


        uri += strRequest + "/marcas/";
        json = requestM.request(uri);

        if(requestM.validJson(json)) {
            var mark = dataM.converterList(json, ResponseFipe.class);
            mark.forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.name()));
        } else {
            throw new InvalidVehicleTypeException("Ops! Please, don't have this option for to vehicle. Check your search.");
        }

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        uri += strRequest+"/modelos/";

        json = requestM.request(uri);
        ModelFipe model = dataM.converter(json, ModelFipe.class);

        if(requestM.validJson(json)) {
        model = dataM.converter(json, ModelFipe.class);
            model.modelos()
                    .stream()
                    .sorted(Comparator.comparing(ResponseFipe::code))
                    .forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.name()));
        } else {
            throw new NotFindException("Ops! Don't finding this option. Check your search.");
        }

        System.out.println("""
                **** OPÇÔES ****
                write the name
                """);

        var nameVehicle = scIn.nextLine();
        var search = model.modelos().stream().filter(v -> v.name().toLowerCase().contains(nameVehicle.toLowerCase())).collect(Collectors.toList());

        search.forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.name()));

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        uri += strRequest+"/anos/";

        json = requestM.request(uri);

        if(requestM.validJson(json)) {
            var year = dataM.converterList(json, ResponseFipe.class);
            var vehicles = year.stream()
                    .map(v -> requestM.request(uri+v.code()))
                    .map(v -> dataM.converter(v, VeiculoFipe.class))
                    .toList();
            vehicles.forEach(System.out::println);
        } else {
            throw new NotFindException("Ops! Don't finding this option. Check your search.");
        }



    }

}
