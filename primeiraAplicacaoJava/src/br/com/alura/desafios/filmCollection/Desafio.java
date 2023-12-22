package br.com.alura.desafios.filmCollection;

import java.util.ArrayList;

public class Desafio {
    public static void main(String[] args) {
        
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        pessoas.add(new Pessoa("leonardo", 20, "branca"));
        pessoas.add(new Pessoa("mariana", 12, "branca"));
        pessoas.add(new Pessoa("Antônio", 40, "preta"));

        System.out.println(pessoas.size());
        System.out.println(pessoas.get(0).getName());
        System.out.println(pessoas);
    
    }
}

class Pessoa{

    private String name;
    private int idade;
    private String color;
    
    public Pessoa() {
        
    }

    public Pessoa(String name, int idade, String color) {
        this.name = name;
        this.idade = idade;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }   
    
    @Override
    public String toString() {
        return "Nome: " + name +", idade: "+ idade+", cor: "+ color;
    }
}
