package br.com.alura.desafios.list.utils;

public class Card {

    private Double limit;

    public Card(Double limit) {
        this.limit = limit;
    }

    public Double getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "limit=" + limit +
                '}';
    }
}
