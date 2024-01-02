package br.com.alura.desafios.segundoDesafio.resource;

public class FilterClas {

    public void filter(Classification clas){

        double aux = clas.getClassificationFilter();

        System.out.println("Like per people: "+ clas.getClassificationFilter());
        System.out.print("Audio: ");
        if(aux >= 0.8)
            System.out.println("it's very very good");
        else if(aux > 0.7)
            System.out.println("It's very good");
        else if (aux > 0.5)
            System.out.println("It's good");
        else
            System.out.println("listen later");
    }


}
