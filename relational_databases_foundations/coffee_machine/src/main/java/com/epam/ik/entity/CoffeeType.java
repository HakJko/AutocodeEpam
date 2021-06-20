package com.epam.ik.entity;

public enum CoffeeType {

    ESPRESSO(250, 0, 16, 1, 15),
    LATTE(350, 75, 20, 1, 25),
    CAPPUCCINO(200, 100, 12, 1, 25);

    public int water;
    public int milk;
    public int beans;
    public int cups;
    public int money;

    CoffeeType(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }
}
