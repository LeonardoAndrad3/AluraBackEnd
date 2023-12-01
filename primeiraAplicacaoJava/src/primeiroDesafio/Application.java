package primeiroDesafio;

import java.util.Scanner;

public class Application {
    Scanner sc = new Scanner(System.in);
    String name = "";
    String accountType = "";
    Double money = 0.0;

    public static void main(String[] args) {

        new Application().initData();

    }

    public void initData(){

        System.out.println("***********************");
        System.out.println("Create account client:");

        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Account type: ");
        accountType = sc.nextLine();

        System.out.print("Money: ");
        money = sc.nextDouble();

        System.out.println("***********************");
        System.out.println();

        operations();

    }

    public void operations(){
        int aux = 0;



        while(aux != 4){
            System.out.println("***********************");

            System.out.println("""
                    1- Consultar saldos
                    2- Receber valor
                    3- Transferir valor
                    4- Sair
                    """);

            System.out.print("Type want option: ");

            aux = sc.nextInt();

            switch (aux){
                case 1:
                    System.out.println("***********************");
                    infoRent();
                    System.out.println();
                break;

                case 2:
                    System.out.println("***********************");

                    System.out.print("Info the value to receive: ");
                    money += sc.nextDouble();

                    System.out.println();

                    infoRent();

                    System.out.println();
                break;

                case 3:
                    System.out.println("***********************");

                    System.out.print("Info the value to transfer: ");
                    double transfer = sc.nextDouble();

                    if(transfer <= money)
                        money -= transfer;
                    else{
                        System.out.println();
                        System.out.println("***********************");
                        System.out.println("You not transfer this value, money not available");
                    }



                    System.out.println();

                    infoRent();


                    System.out.println();
                break;

                case 4:
                    System.out.println("***********************");

                    System.out.println("Thanks, good bye!");

                    System.out.println("***********************");
                    break;

                default:
                    System.out.println("***********************");

                    System.out.println("Option not find");
            }
        }
    }

    public void infoRent(){
        System.out.println("Your money: R$ "+money);
    }



}
