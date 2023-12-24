package br.com.alura.desafios.list.utils;

import java.util.Comparator;

public class Item implements Comparable<Item>{

    private String name;
    private Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - %.1f", name, price);
    }

    @Override
    public int compareTo(Item other) {
        return switch (this.price.compareTo(other.getPrice())) {
            case 1 -> 1;
            case -1 -> -1;
            case 0 -> 0;
            default -> -2;
        };
    }
}
