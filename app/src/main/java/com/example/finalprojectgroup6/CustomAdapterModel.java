package com.example.finalprojectgroup6;

public class CustomAdapterModel {
    private String name;
    private String symbol;
    private double price;
    private int id;

    //Default class with value passing
    public CustomAdapterModel(String name, String symbol, double price,int id) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.id = id;
    }

    //Get Set methods for every variable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


