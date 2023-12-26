package br.com.alura.desafios.list.utils;

public class Card {

    private Double balance;

    public Card(Double limit) {
        this.balance = limit;
    }

    public Double getBalance(){
        return balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "balance=" + balance +
                '}';
    }

    public void stringBalance(){

        String str = String.format("Card balance: R$ %.1f", balance);
        System.out.println(str);

    }

    public boolean acceptBuy(Double price) {
        if(price <= balance) {
            balance -= price;
            return true;
        } else
            return false;
    }
}
