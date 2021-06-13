package com.epam.ik.entity;

public enum ResultState {
    NONE(0),
    NO_WATER(0),
    NO_MILK(0),
    NO_BEANS(0),
    NO_CUPS(0),
    COFFEE_SOLD(0),
    MONEY_BACK(0);

    public int amount;

    ResultState(int amount) {
        this.amount = amount;
    }

}
