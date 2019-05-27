package com.cashdash.finalproject.Model;

public class Order {

    String id,name,address,number,amount;

    public Order()
    {

    }

    public Order(String id, String name, String address, String number, String amount)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
