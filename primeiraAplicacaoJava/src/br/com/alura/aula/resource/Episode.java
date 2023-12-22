package br.com.alura.aula.resource;

import br.com.alura.aula.resource.math.Classification;

public class Episode implements Classification {

    private int number;
    private String name;
    private Serie serie;
    private int totalVisualization;

    public int getTotalVisualization() {
        return totalVisualization;
    }

    public void setTotalVisualization(int totalVisualization) {
        this.totalVisualization = totalVisualization;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int getClassification() {
        return totalVisualization <= 100 ? 4 : 2;
    }

}
