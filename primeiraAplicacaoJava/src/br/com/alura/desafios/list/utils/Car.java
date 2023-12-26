package br.com.alura.desafios.list.utils;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private List<Item> storage = new ArrayList<>();
    private Double spendings;

    public void addItem(Item item){
        storage.add(item);
    }

    public void removeItem(Item item){
        storage.remove(item);
    }

    public void showAllItem(){
        for(Item item : storage){
            System.out.println(item);
        }
    }

    public void closeShopping(){
        String str = String.format("Total: R$ %.1f", storage.stream().mapToDouble(Item::getTotal).sum());

        System.out.println("Purchases realized: \n");

        for(Item item : storage){

            System.out.println(item + "\n");
        }

        System.out.println(str);
    }

}
