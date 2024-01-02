package br.com.alura.aula.resource.math;

public class RecommendationFilter {

    private String recommendation;

    public void filtrar(Classification clas){
        if(clas.getClassification() >= 4)
            System.out.println("best the moment");
        else if (clas.getClassification() >= 2)
            System.out.println("It's good rating");
        else
            System.out.println("See will this later");
    }


}
