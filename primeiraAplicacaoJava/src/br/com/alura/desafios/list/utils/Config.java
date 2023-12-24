package br.com.alura.desafios.list.utils;

import br.com.alura.desafios.list.utils.Car;
import br.com.alura.desafios.list.utils.Item;

public class Config {
    
    public static Item mouse = new Item("Mouse", 120.00);
    public static Item teclado = new Item("Teclado", 50.00);
    public static Item monitor = new Item("Monitor", 300.00);
    public static Item foneDeOuvido = new Item("Fone de Ouvido", 80.00);
    public static Item camera = new Item("Câmera", 200.00);
    public static Item impressora = new Item("Impressora", 150.00);
    public static Item notebook = new Item("Notebook", 800.00);
    public static Item tablet = new Item("Tablet", 250.00);
    public static Item smartphone = new Item("Smartphone", 600.00);
    public static Item caixaDeSom = new Item("Caixa de Som", 30.00);

    public Car createCar(){
        return new Car();
    }
}
