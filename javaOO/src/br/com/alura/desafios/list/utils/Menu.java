package br.com.alura.desafios.list.utils;

import java.util.Scanner;

public class Menu{
    private Card card;
    private Car car = new Car();
    private Scanner sc = new Scanner(System.in);

    public void run(){
        menu();
    }

    private void menu(){
        String name;
        Double price;
        Integer number;

        System.out.println("Welcome the shopping");
        System.out.print("Please, write the card limit: ");
        card = new Card(sc.nextDouble());

        do {
            System.out.print("\nWrite the type product: ");
            sc.nextLine();
            name = sc.nextLine();
            System.out.print("Write the price: ");
            price = sc.nextDouble();
            System.out.print("What's it quantity: ");
            number =  sc.nextInt();

            if(card.acceptBuy(price*number))
                if(number != 0){
                    car.addItem(new Item(name, price, number));
                    System.out.println("\npurchase realized!");
                    card.stringBalance();
                } else
                    System.out.println("Opss! You cannot buy zero items");
            else {
                System.out.println("\nWithout account balance!\n");
                break;
            }

            System.out.print("\n| 0 - EXIT     |\n" +
                               "| 1 - CONTINUE |\n");

        } while(sc.nextInt() != 0);

        car.closeShopping();
        card.stringBalance();
    }

}
