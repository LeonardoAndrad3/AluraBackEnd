package br.com.stream.challenge.application;

import br.com.stream.challenge.exceptions.InvalidVehicleTypeException;
import br.com.stream.challenge.exceptions.NotFindException;
import br.com.stream.challenge.model.ModelFipe;
import br.com.stream.challenge.model.ResponseFipe;
import br.com.stream.challenge.model.VeiculoFipe;
import br.com.stream.challenge.utils.DataManager;
import br.com.stream.challenge.utils.IScanner;
import br.com.stream.challenge.utils.RequestManager;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.stream.Collectors;

public class App implements IScanner {

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

        uri += strRequest + "/marcas/";
        json = requestM.request(uri);

        if(requestM.validJson(json)) {
            var mark = dataM.converter(json, new TypeReference<List<ResponseFipe>>() {
            });
            mark.forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.tag()));
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

        if(requestM.validJson(json)) {
        var model = dataM.converter(json, ModelFipe.class);
            model.list().forEach(v -> System.out.printf("Cód: %s Descrição: %s%n", v.code(), v.tag()));
        } else {
            throw new NotFindException("Ops! Don't finding this option. Check your search.");
        }

        System.out.println("""
                **** OPÇÔES ****
                write the code
                """);
        strRequest = scIn.nextLine();

        uri += strRequest+"/anos/";

        json = requestM.request(uri);

        if(requestM.validJson(json)) {
            var year = dataM.converter(json, new TypeReference<List<ResponseFipe>>(){});
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
