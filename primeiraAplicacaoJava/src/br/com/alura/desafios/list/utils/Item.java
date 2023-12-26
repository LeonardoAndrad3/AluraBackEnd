package br.com.alura.desafios.list.utils;

import java.util.Comparator;

public class Item implements Comparable<Item>{

    private String name;
    private Double price;
    private Integer number;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, Double price, Integer number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotal(){
        return price*number;
    }


    @Override
    public String toString() {
        return String.format("%dX %s(R$ %.1f) - R$ %.1f", number, name, price, price*number);
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
