package com.epam.ik.entity;

public class Machine {
    public int water;
    public int milk;
    public int beans;
    public int cups;
    public int money;
    private MachineState state;

    public Machine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.state = MachineState.AWAIT;
    }

    private ResultState buyCoffee(CoffeeType coffeeType) {
        if (coffeeType.water > water) {
            return ResultState.NO_WATER;
        }
        if (coffeeType.milk > milk) {
            return ResultState.NO_MILK;
        }
        if (coffeeType.beans > beans) {
            return ResultState.NO_BEANS;
        }
        if (coffeeType.cups > cups) {
            return ResultState.NO_CUPS;
        }
        water -= coffeeType.water;
        milk -= coffeeType.milk;
        beans -= coffeeType.beans;
        cups -= coffeeType.cups;
        money += coffeeType.money;
        return ResultState.COFFEE_SOLD;
    }

    public ResultState manage(String action) {
        switch (state) {
            case AWAIT:
                state = MachineState.valueOf(action);
                break;
            case TAKE_MONEY:
                ResultState response = ResultState.MONEY_BACK;
                response.amount = money;
                money = 0;
                state = MachineState.AWAIT;
                return response;
            case BUY:
                CoffeeType coffeeType = CoffeeType.valueOf(action);
                response = buyCoffee(coffeeType);
                state = MachineState.AWAIT;
                return response;
            case FILL_WATER:
                water += Integer.parseInt(action);
                state = MachineState.AWAIT;
                break;
            case FILL_MILK:
                milk += Integer.parseInt(action);
                state = MachineState.AWAIT;
                break;
            case FILL_BEANS:
                beans += Integer.parseInt(action);
                state = MachineState.AWAIT;
                break;
            case FILL_CUPS:
                cups += Integer.parseInt(action);
                state = MachineState.AWAIT;
                break;
            default:
                break;
        }
        return ResultState.NONE;
    }
}
